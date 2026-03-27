package app;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Laptop", "Electronics", 1200.0),
                new Product("Headphones", "Electronics", 150.0),
                new Product("Phone", "Electronics", 800.0),
                new Product("Coffee Maker", "Appliances", 80.0),
                new Product("Blender", "Appliances", 50.0),
                new Product("Microwave", "Appliances", 200.0),
                new Product("Bread", "Food", 25.0),
                new Product("Milk", "Food", 40.0),
                new Product("Cheese", "Food", 120.0)
        );

        //групування за категоріями
        Map<String, List<Product>> groupedProducts = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));
        System.out.println("Products, groups in category: ");
        groupedProducts.forEach((category, productList) -> {
            System.out.println(category + " -> " + productList);
        });
        System.out.println();

        //average expensive in each categ.
        Map<String, Double> averagePrices = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.averagingDouble(Product::getPrice)
                ));
        System.out.println("Average expensive products in each category: ");
        averagePrices.forEach((category, avgPrice) -> {
            System.out.println(category + " -> " + avgPrice);
        });

        System.out.println();
        //categ. with high average price

        Optional<Map.Entry<String, Double>> maxCategory = averagePrices.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());
        if (maxCategory.isPresent()) {
            System.out.println("Category with the high average price: "
                    + maxCategory.get().getKey());
            System.out.println("Average price: " + maxCategory.get().getValue());
        } else {
            System.out.println("List products empty!");
        }



    }
}