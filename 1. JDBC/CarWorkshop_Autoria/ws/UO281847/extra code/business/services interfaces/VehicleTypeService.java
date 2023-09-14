package uo.ri.cws.application.business.vehicletype;

/**
 * This service is intended to be used by the Cashier
 * It follows the ISP principle (@see SOLID principles from RC Martin)
 */
public interface VehicleTypeService {

    // ...

    public static class VehicleTypeBLDto {

        public String id;
        public long version;

        public String name;
        public double pricePerHour;
        public int minTrainigHours;

    }
}
