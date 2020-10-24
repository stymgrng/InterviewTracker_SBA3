package com.eval.coronakit.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eval.coronakit.entity.CoronaKit;
import com.eval.coronakit.entity.KitDetail;
import com.eval.coronakit.entity.ProductMaster;
import com.eval.coronakit.service.CoronaKitService;
import com.eval.coronakit.service.KitDetailService;
import com.eval.coronakit.service.ProductService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	ProductService productService;

	@Autowired
	CoronaKitService coronaKitService;

	@Autowired
	KitDetailService kitDetailService;

	HttpSession session;

	@RequestMapping("/home")
	public String home() {
		return "user-home";
	}

	@RequestMapping("/show-kit")
	public String showKit(Model model) {
		return "show-cart";
	}

	@RequestMapping("/show-list")
	public String showList(Model model) {

		List<ProductMaster> productList = this.productService.getAllProducts();
		model.addAttribute("productList", productList);

		return "show-all-item-user";
	}

	@RequestMapping("/add-to-cart/{productId}")
	public String showKit(@PathVariable("productId") int productId, HttpSession session) {

		ProductMaster product = productService.getProductById(productId);

		if(product!=null) {

			if (session.getAttribute("CartDetails") == null) {

				List<KitDetail> cartDetails = new ArrayList<KitDetail>();

				KitDetail kit = new KitDetail();
				kit.setProductId(product.getId());
				kit.setProductName(product.getProductName());
				kit.setAmount(product.getCost());
				kit.setQuantity(1);

				cartDetails.add(kit);

				session.setAttribute("CartDetails", cartDetails);

			} else {

				List<KitDetail> cartDetails = (List<KitDetail>) session.getAttribute("CartDetails");

				int index = getIndexOfItemAddedToCart(productId, cartDetails);

				if (index == -1) {

					KitDetail kit = new KitDetail();
					kit.setProductId(product.getId());
					kit.setProductName(product.getProductName());
					kit.setAmount(product.getCost());
					kit.setQuantity(1);

					cartDetails.add(kit);

				} else {

					int quantity = cartDetails.get(index).getQuantity();
					cartDetails.get(index).setQuantity(quantity + 1);

				}

				session.setAttribute("CartDetails", cartDetails);
			}
		}
		return "redirect:/user/show-kit";
	}


	@RequestMapping("/checkout")
	public String checkout(Model model) {

		CoronaKit coronaKit = new CoronaKit();
		model.addAttribute("CoronaKit", coronaKit);

		return "checkout-address";

	}

	@RequestMapping("/finalize")
	public String finalizeOrder(@RequestParam("deliveryAddress") String address, Model model, HttpSession session) {

		List<KitDetail> cartDetails = (List<KitDetail>) session.getAttribute("CartDetails");

		CoronaKit coronaKit = new CoronaKit();
		coronaKit.setDeliveryAddress(address);
		coronaKit.setOrderDate(LocalDateTime.now().toString());
		coronaKit.setTotalAmount(getCartTotal(cartDetails));

		coronaKit = coronaKitService.saveKit(coronaKit);

		List<KitDetail> orderedKits = new ArrayList<KitDetail>();
		for (int i = 0; i < cartDetails.size(); i++) {

			KitDetail kit = cartDetails.get(i);

			kit.setCoronaKitId(coronaKit.getId());
			kit.setAmount(kit.getAmount() * kit.getQuantity());
			orderedKits.add(kitDetailService.addKitItem(kit));
		}

		model.addAttribute("CoronaKit", coronaKit);
		model.addAttribute("OrderedKits", orderedKits);

		return "show-summary";
	}

	@RequestMapping("/delete/{itemId}")
	public String deleteItem(@PathVariable("itemId") int itemId, HttpSession session) {

		List<KitDetail> cartDetails = (List<KitDetail>) session.getAttribute("CartDetails");

		if(cartDetails==null)
			return "redirect:/user/show-list";

		int index = getIndexOfItemAddedToCart(itemId, cartDetails);

		cartDetails.remove(index);

		if (cartDetails.isEmpty()) {

			session.setAttribute("CartDetails", null);
			return "redirect:/user/show-list";
		}

		session.setAttribute("CartDetails", cartDetails);

		return "redirect:/user/show-kit";

	}


	private int getIndexOfItemAddedToCart(int productId, List<KitDetail> cartDetails) {

		for (int index = 0; index < cartDetails.size(); index++) {
			if (cartDetails.get(index).getProductId() == productId)
				return index;
		}
		return -1;
	}

	private int getCartTotal(List<KitDetail> cartDetails) {

		int total = 0;
		for (int i = 0; i < cartDetails.size(); i++)
			total += cartDetails.get(i).getAmount();

		return total;
	}

}