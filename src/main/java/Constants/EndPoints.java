package Constants;

public class EndPoints {

    //UAT_URL="https://tbx.backend.development.travel.theculturetrip.com/";
    //STG_URL="https://backend.staging.travel.theculturetrip.com/"
//    public static final String BaseEnvironmet= "https://backend.staging.travel.theculturetrip.com/";
    public static final String BaseEnvironmet ="https://tbx.backend.development.travel.theculturetrip.com/";
    public static final String SearchURLPara ="hotel-search/v2/products";
    public static final String CreateCartURLPara = "cart-service/v2/cart/-1/products?actions&confirm=false&bkgSource=TC&itemSource=TC&expand=all";
    public static final String PromoPara1 = "cart-service/v2/cart/";
    public static final String PromoPara2 = "/promotions?confirm=false&bkgSource=TC&itemSource=TC&expand=all";
    public static final String UpdatePassengerPara = "/travellers?confirm=false&bkgSource=TC&itemSource=TC&expand=all";
    public static final String PaymentSessionURL = "payment-service/v2/payment/redirect-transactions/STRIPE";
    public static final String AddPaymentPara = "/payments?bkgSource=TC&confirm=false&expand=all";
    public static final String ConfirmPara = "?actions&confirm=true&quote=false&expand=all";
    public static final String UpdateQuestions="/products/GEN~1?confirm=false&bkgSource=TC&itemSource=TC&action=UPDATE_ADDITIONAL_REQUIREMENTS&actions=UPDATE_ADDITIONAL_REQUIREMENTS";
    public static final String CancellationPara1="reservation-service/v2/bookings/";
    public static final String CancellationPara2="/amendment-cancellation-charges";
    public static final String SearchURLParaGen ="generic-search/v2/products";
    public static final String HTLCalendar ="hotel-search/v2/products/calendar";
    public static final String TritiumURL = "http://119.235.9.11:7006/tritium-hotel-search/booking-api/search/hotels";
    public static final String GENCalendar = "generic-search/v2/products/calendar";
}
