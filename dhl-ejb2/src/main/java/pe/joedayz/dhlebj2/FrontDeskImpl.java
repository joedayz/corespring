package pe.joedayz.dhlebj2;

public class FrontDeskImpl implements FrontDesk {
    private PostageService postageService;

    public void setPostageService(PostageService postageService) {
        this.postageService = postageService;
    }

    public double calculatePostage(String country, double weight) {
        return postageService.calculatePostage(country, weight);
    }
}
