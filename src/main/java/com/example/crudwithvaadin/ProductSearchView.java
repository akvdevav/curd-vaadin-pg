// // // ProductSearchView.java
// // package com.example.crudwithvaadin;

// // import com.vaadin.flow.component.button.Button;
// // import com.vaadin.flow.component.grid.Grid;
// // import com.vaadin.flow.component.icon.VaadinIcon;
// // import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
// // import com.vaadin.flow.component.orderedlayout.VerticalLayout;
// // import com.vaadin.flow.component.textfield.TextField;
// // import com.vaadin.flow.data.value.ValueChangeMode;
// // import com.vaadin.flow.router.Route;
// // import org.springframework.util.StringUtils;

// // /**
// //  * A new Vaadin view specifically for product text search.
// //  * This view allows users to input a search term and see matching products
// //  * from the 'products_search' table.
// //  * It is accessible at the "/products" route.
// //  */
// // @Route("search") // This makes the view accessible at http://localhost:8080/products
// // public class ProductSearchView extends VerticalLayout {

// //     private final ProductSearchRepository productSearchRepo; // Inject the ProductSearchRepository

// //     final Grid<ProductSearch> grid; // Grid to display ProductSearch entities

// //     final TextField searchFilter; // Text field for the search query

// //     private final Button searchButton; // Button to trigger the search

// //     /**
// //      * Constructor for ProductSearchView, injecting the ProductSearchRepository.
// //      * Spring automatically provides the instance.
// //      * @param productSearchRepo The repository for performing product searches.
// //      */
// //     public ProductSearchView(ProductSearchRepository productSearchRepo) {
// //         this.productSearchRepo = productSearchRepo;

// //         // Initialize components
// //         this.grid = new Grid<>(ProductSearch.class);
// //         this.searchFilter = new TextField();
// //         this.searchButton = new Button("Search Products", VaadinIcon.SEARCH.create());

// //         // Configure the search filter text field
// //         searchFilter.setPlaceholder("Enter search term (e.g., 'book', 'shoes & shirt')");
// //         searchFilter.setWidth("400px"); // Give it some reasonable width
// //         searchFilter.setValueChangeMode(ValueChangeMode.LAZY); // Delay updates for better performance

// //         // Configure the grid to display relevant product columns
// //         grid.setHeight("400px"); // Set a fixed height for the grid
// //         // Define columns to display from ProductSearch entity
// //         grid.setColumns("prodId", "category", "title", "actor", "price");
// //         // Adjust column widths and flexibility for better presentation
// //         grid.getColumnByKey("prodId").setHeader("Product ID").setWidth("100px").setFlexGrow(0);
// //         grid.getColumnByKey("category").setHeader("Category").setWidth("100px").setFlexGrow(0);
// //         grid.getColumnByKey("title").setHeader("Title").setFlexGrow(1); // Title can take more space
// //         grid.getColumnByKey("actor").setHeader("Actor/Brand").setFlexGrow(1);
// //         grid.getColumnByKey("price").setHeader("Price").setWidth("120px").setFlexGrow(0);
// //         // grid.getColumnByKey("title_tsv").setHeader("title_tsv").setWidth("120px").setFlexGrow(0);

// //         // Build the layout
// //         // Horizontal layout for the search input and button
// //         HorizontalLayout searchControls = new HorizontalLayout(searchFilter, searchButton);
// //         searchControls.setDefaultVerticalComponentAlignment(Alignment.BASELINE); // Align items nicely
// //         add(searchControls, grid); // Add controls and grid to the main VerticalLayout

// //         // Hook logic to components

// //         // Trigger search when user presses Enter in the search field or on value change (lazy)
// //         searchFilter.addValueChangeListener(e -> listProducts(e.getValue()));
// //         // Trigger search when the search button is clicked
// //         searchButton.addClickListener(e -> listProducts(searchFilter.getValue()));

// //         // Initialize the listing with no filter, showing all products initially (or an empty grid)
// //         listProducts(null); // Load all products or an empty list on startup
// //     }

// //     /**
// //      * Lists products based on the provided filter text.
// //      * If filterText is empty, it fetches all products.
// //      * Otherwise, it performs a full-text search using the ProductSearchRepository.
// //      * @param filterText The text to search for in product titles.
// //      */
// //     void listProducts(String filterText) {
// //         if (StringUtils.hasText(filterText)) {
// //             // Perform text search using the repository's custom query.
// //             // The 'to_tsquery' function requires specific input formatting (e.g., 'term1 & term2').
// //             // If you want more forgiving user input, consider using 'plainto_tsquery' in the repository.
// //             grid.setItems(productSearchRepo.searchText(filterText));
// //         } else {
// //             // If no filter text, fetch all products. Be cautious with large datasets here.
// //             grid.setItems(productSearchRepo.findAll());
// //         }
// //     }
// // }


// // ProductSearchView.java
// package com.example.crudwithvaadin;

// import com.vaadin.flow.component.button.Button;
// import com.vaadin.flow.component.grid.Grid;
// import com.vaadin.flow.component.icon.VaadinIcon;
// import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
// import com.vaadin.flow.component.orderedlayout.VerticalLayout;
// import com.vaadin.flow.component.textfield.TextField;
// import com.vaadin.flow.data.value.ValueChangeMode;
// import com.vaadin.flow.router.Route;
// import org.springframework.util.StringUtils;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// import java.util.List;

// /**
//  * A new Vaadin view specifically for product text search.
//  * This view allows users to input a search term and see matching products
//  * from the 'products_search' table.
//  * It is accessible at the "/products" route.
//  */
// @Route("search") // This makes the view accessible at http://localhost:8080/search
// public class ProductSearchView extends VerticalLayout {

//     private static final Logger logger = LoggerFactory.getLogger(ProductSearchView.class);

//     private final ProductSearchRepository productSearchRepo; // Inject the ProductSearchRepository

//     final Grid<ProductSearch> grid; // Grid to display ProductSearch entities

//     final TextField searchFilter; // Text field for the search query

//     private final Button searchButton; // Button to trigger the search

//     /**
//      * Constructor for ProductSearchView, injecting the ProductSearchRepository.
//      * Spring automatically provides the instance.
//      * @param productSearchRepo The repository for performing product searches.
//      */
//     public ProductSearchView(ProductSearchRepository productSearchRepo) {
//         this.productSearchRepo = productSearchRepo;

//         this.grid = new Grid<>(ProductSearch.class); // Initialize the Grid with ProductSearch type
//         this.searchFilter = new TextField();
//         this.searchButton = new Button("Search", VaadinIcon.SEARCH.create());

//         // Configure search filter
//         searchFilter.setPlaceholder("Search by title...");
//         searchFilter.setClearButtonVisible(true);
//         searchFilter.setValueChangeMode(ValueChangeMode.LAZY); // Search on value change with a slight delay

//         // Layout components
//         HorizontalLayout toolbar = new HorizontalLayout(searchFilter, searchButton);
//         add(toolbar, grid); // Add controls and grid to the main VerticalLayout

//         // Hook logic to components

//         // Trigger search when user presses Enter in the search field or on value change (lazy)
//         searchFilter.addValueChangeListener(e -> listProducts(e.getValue()));
//         // Trigger search when the search button is clicked
//         searchButton.addClickListener(e -> listProducts(searchFilter.getValue()));

//         // Configure Grid columns
//         grid.setColumns("prodId", "category", "title", "actor", "price"); // Display these columns

//         // Initialize the listing with no filter, showing all products initially (or an empty grid)
//         listProducts(null); // Load all products or an empty list on startup
//     }

//     /**
//      * Lists products based on the provided filter text.
//      * If filterText is empty, it fetches all products.
//      * Otherwise, it performs a full-text search using the ProductSearchRepository.
//      * @param filterText The text to search for in product titles.
//      */
//     void listProducts(String filterText) {
//         logger.info("Initiating product list refresh with filter: [{}]", filterText);
//         List<ProductSearch> products;

//         if (StringUtils.hasText(filterText)) {
//             // Perform text search using the repository's custom query.
//             products = productSearchRepo.searchText(filterText);
//             logger.info("Found {} products for search term: [{}]", products.size(), filterText);
//         } else {
//             // If no filter text, fetch all products. Be cautious with large datasets here.
//             products = productSearchRepo.findAll();
//             logger.info("Found {} total products (no filter).", products.size());
//         }

//         // Log the actual data being sent to the grid
//         products.forEach(product -> logger.debug("Product fetched: {}", product));

//         grid.setItems(products);
//         logger.info("Grid updated with {} items.", products.size());
//     }
// }


// ProductSearchView.java
package com.example.crudwithvaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * A new Vaadin view specifically for product text search.
 * This view allows users to input a search term and see matching products
 * from the 'products_search' table.
 * It is accessible at the "/products" route.
 */
@Route("search") // This makes the view accessible at http://localhost:8080/search
public class ProductSearchView extends VerticalLayout {

    private static final Logger logger = LoggerFactory.getLogger(ProductSearchView.class);

    private final ProductSearchRepository productSearchRepo; // Inject the ProductSearchRepository

    final Grid<ProductSearch> grid; // Grid to display ProductSearch entities

    final TextField searchFilter; // Text field for the search query

    private final Button searchButton; // Button to trigger the search

    /**
     * Constructor for ProductSearchView, injecting the ProductSearchRepository.
     * Spring automatically provides the instance.
     * @param productSearchRepo The repository for performing product searches.
     */
    public ProductSearchView(ProductSearchRepository productSearchRepo) {
        this.productSearchRepo = productSearchRepo;

        this.grid = new Grid<>(ProductSearch.class); // Initialize the Grid with ProductSearch type
        this.searchFilter = new TextField();
        this.searchButton = new Button("Search", VaadinIcon.SEARCH.create());

        // Configure search filter
        searchFilter.setPlaceholder("Search by title...");
        searchFilter.setClearButtonVisible(true);
        searchFilter.setValueChangeMode(ValueChangeMode.LAZY); // Search on value change with a slight delay

        // Layout components
        HorizontalLayout toolbar = new HorizontalLayout(searchFilter, searchButton);
        add(toolbar, grid); // Add controls and grid to the main VerticalLayout

        // Hook logic to components

        // Trigger search when user presses Enter in the search field or on value change (lazy)
        searchFilter.addValueChangeListener(e -> listProducts(e.getValue()));
        // Trigger search when the search button is clicked
        searchButton.addClickListener(e -> listProducts(searchFilter.getValue()));

        // Configure Grid columns to include title_tsv
        grid.setColumns("prodId", "category", "title", "actor", "price", "title_tsv"); // Display these columns

        // Initialize the listing with no filter, showing all products initially (or an empty grid)
        listProducts(null); // Load all products or an empty list on startup
    }

    /**
     * Lists products based on the provided filter text.
     * If filterText is empty, it fetches all products.
     * Otherwise, it performs a full-text search using the ProductSearchRepository.
     * @param filterText The text to search for in product titles.
     */
    void listProducts(String filterText) {
        logger.info("Initiating product list refresh with filter: [{}]", filterText);
        List<ProductSearch> products;

        if (StringUtils.hasText(filterText)) {
            // Log the count of matching records for diagnostic purposes
            Long count = productSearchRepo.countText(filterText);
            logger.info("Database reports [{}] records for search term: [{}]", count, filterText);

            // Perform text search using the repository's custom query (now using plainto_tsquery).
            products = productSearchRepo.searchText(filterText);
            logger.info("Found {} products returned by searchText method for search term: [{}]", products.size(), filterText);
        } else {
            // If no filter text, fetch all products. Be cautious with large datasets here.
            products = productSearchRepo.findAll();
            logger.info("Found {} total products (no filter).", products.size());
        }

        // Log the actual data being sent to the grid
        products.forEach(product -> logger.debug("Product fetched: {}", product));

        grid.setItems(products);
        logger.info("Grid updated with {} items.", products.size());
    }
}

