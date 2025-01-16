package com.batch.example.config;

import com.batch.example.model.Product;
import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor implements ItemProcessor<Product, Product> {
    @Override
    public Product process(Product item) throws Exception {
//        transform ka logic yaha likha jayega
//        calulate karenge discounted price ko
//        original  price
//        discount

        try {
//            put the percentage logic
            System.out.println(item.getDescription());
            int discountPer = Integer.parseInt(item.getDiscount().trim());
            double originalPrice = Double.parseDouble(item.getPrice().trim());
            double discount = (discountPer / 100) * originalPrice;
            double finalPrice = originalPrice - discount;
            item.setDiscountedPrice(String.valueOf(finalPrice));
        } catch (
                NumberFormatException ex
        ) {
            ex.printStackTrace();
        }

        return item;
    }
}



//package com.batch.example.config;
//
//import org.springframework.batch.item.ItemProcessor;
//
//import com.batch.example.model.Product;
//
//public class CustomItemProcessor implements ItemProcessor<Product, Product> {
//
//	@Override
//	public Product process(Product item) throws Exception {
		
		// Here we will write transform logic
		// Here we will Calculate discounted price 
		// original price
		// discount
		//int discountPer =  Integer.parseInt(item.getDiscount().trim());
		//double originalPrice = Double.parseDouble(item.getPrice().trim());
		// Calculate discounted price
		//double discount = (discountPer/100)*originalPrice;
		// get the final price
		//double finalPrice =originalPrice-discount;
		//Convert finalPrice price in string
		
		//item.setDiscountedPrice(String.valueOf(finalPrice));
		
		//return item;
		
	//}

//}
