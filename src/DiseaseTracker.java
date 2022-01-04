import controller.DiseaseTrackerController;

public class DiseaseTracker {

    public static void main(String[] args) {

        DiseaseTrackerController diseaseTrackerController = new DiseaseTrackerController();

        diseaseTrackerController.registerDisease("COVID-19", "VIRAL", "TRANSMISSIBLE", "AIR");
        diseaseTrackerController.registerDisease("HUNGER", "ECONOMY", "NON-TRANSMISSIBLE", "NONE");
        diseaseTrackerController.registerDisease("TYPHOID", "Bacterial", "TRANSMISSIBLE", "WATER");
//        diseaseTrackerController.registerDisease("Spanish Flu", "Bacterial", "TRANSMISSIBLE", "WATER");


        //reporting disease & Saving person
        diseaseTrackerController.report("COVID-19", "P1", "China", "Hubei", "Wuhan");
        diseaseTrackerController.report("COVID-19", "P2", "China", "Hubei", "Wuhan");
        diseaseTrackerController.report("COVID-19", "P3", "China", "Jiangsu", "Shanghai");
        diseaseTrackerController.report("COVID-19", "P4", "India", "Uttar Pradesh", "Agra");
        diseaseTrackerController.report("COVID-19", "P5", "India", "Uttar Pradesh", "Varanasi");
        diseaseTrackerController.report("COVID-19", "P6", "India", "Karnataka", "Bangalore");
        diseaseTrackerController.report("COVID-19", "P7", "India", "Karnataka", "Bangalore");
        diseaseTrackerController.report("COVID-19", "P8", "India", "Karnataka", "Bangalore");
        diseaseTrackerController.report("Spanish Flu", "P9", "USA", "California", "Los Angeles");
        diseaseTrackerController.report("Spanish Flu", "P10", "USA", "California", "San-francisco");

        diseaseTrackerController.showWorldSummary();

        diseaseTrackerController.ShowWorldSummaryDiseasesBreakup();

        diseaseTrackerController.ShowCountryBreakup("COVID-19");

        diseaseTrackerController.cured("COVID-19", "P8");
        diseaseTrackerController.cured("Spanish Flu", "P8");

        diseaseTrackerController.ShowCountryBreakup("COVID-19");

        diseaseTrackerController.registerFatility("COVID-19", "P2");

        diseaseTrackerController.ShowCountryBreakup("COVID-19");

    }

}
