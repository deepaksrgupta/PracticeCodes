interface Company {
    String getCompany();
}

class Android implements Company{
    private final String name;

    Android() {
        this.name = "Android";
    }

    @Override
    public String getCompany() {
        return name;
    }
}

class Google implements Company {
    private final String name;

    Google(){
        this.name = "Google";
    }

    @Override
    public String getCompany() {
        return name;
    }
}

interface Products {
    String getProduct();
}

class Mobile implements Products {

    private final String product = "Mobile";
    @Override
    public String getProduct() {
        return product;
    }
}

class SearchEngine implements Products {
    private final String product = "SearchEngine";
    @Override
    public String getProduct() {
        return product;
    }
}

abstract class AbstractFactoryMethod{
    public abstract Company getCompany(String company);
    public abstract Products getProducts(String productName);
}

class CompanyFactory extends AbstractFactoryMethod {

    @Override
    public Company getCompany(String company) {
        if(company == "Google")
            return new Google();
        else return new Android();
    }

    @Override
    public Products getProducts(String productName) {
        return null;
    }
}

class ProductFactory extends AbstractFactoryMethod {

    @Override
    public Company getCompany(String company) {
        return null;
    }

    @Override
    public Products getProducts(String productName) {
        if(productName == "Mobile"){
            return new Mobile();
        }else{
            return new SearchEngine();
        }
    }
}

public class AbstractFactory {
    public static AbstractFactoryMethod getFactory(String factoryName){
        if (factoryName == "Product") {
            return new ProductFactory();
        }else{
            return new CompanyFactory();
        }
    }
}

//    public static void main(String args[]) {
//        AbstractFactory factory = new AbstractFactory();
//
//        AbstractFactoryMethod product = factory.getFactory("Product");
//        Products searchEngine = product.getProducts("SearchEngine");
//        System.out.println(searchEngine.getProduct());
//
//        AbstractFactoryMethod companyFactory = factory.getFactory("Company");
//        Company company = companyFactory.getCompany("Google");
//        System.out.println(company.getCompany());
//
//    }
