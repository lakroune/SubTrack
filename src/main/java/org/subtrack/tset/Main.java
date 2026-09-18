// package org.subtrack.tset;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// public class Main {
//     public static void main(String[] args) {

//         Map<String, List<Livre>> catalogue = new HashMap<>();

//         List<Livre> livresSF = new ArrayList<>();
//         livresSF.add(new Livre("Dune", 22.50, "Frank Herbert"));
//         livresSF.add(new Livre("Fondation", 15.90, "Isaac Asimov"));
//         catalogue.put("Science-Fiction", livresSF);

//         List<Livre> livresInfo = new ArrayList<>();
//         livresInfo.add(new Livre("Clean Code", 35.00, "Robert C. Martin"));
//         livresInfo.add(new Livre("Effective Java", 42.00, "Joshua Bloch"));
//         catalogue.put("Informatique", livresInfo);

//         catalogue.Stream()
//                 .forEach((key, value) -> System.out.println(key + " : " + value));
//     }
// }