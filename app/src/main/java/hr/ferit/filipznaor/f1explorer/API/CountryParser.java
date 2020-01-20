package hr.ferit.filipznaor.f1explorer.API;

public class CountryParser {
    public static String getCountryCode(String phrase){
        switch(phrase){
            case "Italian" :
            case "Italy" : return "it";
            case "British" :
            case "UK" : return "gb";
            case "Belgian" :
            case "Belgium" : return "be";
            case "American" :
            case "American-Italian" :
            case "USA" : return "us";
            case "German" :
            case "Germany" : return "de";
            case "Dutch" :
            case "Netherlands" : return "nl";
            case "Thai" : return "th";
            case "French" :
            case "France" : return "fr";
            case "Spanish" :
            case "Spain" : return "es";
            case "New Zealander" :
            case "New Zealand" : return "nz";
            case "Swedish" :
            case "Sweden" : return "se";
            case "Brazilian" :
            case "Brazil" : return "br";
            case "Hungarian" :
            case "Hungary" :return "hu";
            case "Danish" : return "dk";
            case "Monegasque" :
            case "Monaco" : return "mc";
            case "Canadian" :
            case "Canada" : return "ca";
            case "Austrian" :
            case "Austria": return "at";
            case "Argentine" :
            case "Argentina": return "ar";
            case "South African" :
            case "South Africa" : return "za";
            case "Finnish" : return "fi";
            case "Swiss" :
            case "Switzerland" : return "ch";
            case "Irish" : return "ie";
            case "Portuguese" :
            case "Portugal" : return "pt";
            case "Uruguayan" : return "uy";
            case "Venezuelan" : return "ve";
            case "Indian" :
            case "India" : return "in";
            case "Argentine-Italian" : return "ar";
            case "Czech" : return "cz";
            case "Australian" :
            case "Australia": return "au";
            case "East German" : return "de";
            case "Japanese" :
            case "Japan" : return "jp";
            case "Colombian" : return "co";
            case "Mexican" :
            case "Mexico" : return "mx";
            case "Indonesian" : return "id";
            case "Russian" :
            case "Russia" : return "ru";
            case "Polish" : return "pl";
            case "Rhodesian" : return "za";
            case "Chilean" : return "cl";
            case "Liechtensteiner" : return "li";
            case "Malaysian" :
            case "Malaysia" : return "my";
            case "Hong Kong" : return "hk";
            case "Azerbaijan" : return "az";
            case "Bahrain" : return "bh";
            case "China" : return "cn";
            case "Korea" : return "kr";
            case "Morocco" : return "ma";
            case "Singapore" : return "sg";
            case "Turkey" : return "tr";
            case "UAE" : return "ae";
            case "Vietnam" : return "vn";
            default: return "xx";
        }
    }
}
