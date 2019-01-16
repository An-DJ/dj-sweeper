package me.andj.sweeper.domain;

public class ReturnInformation {
    private String code;
    private String information;
    public ReturnInformation(String code,String information){
        this.code=code;
        this.information=information;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public static class ReturnInformationBuilder{
        public static ReturnInformation get101(){
            return new ReturnInformation("101","log up failed");
        }
        public static ReturnInformation get102(){
            return new ReturnInformation("102","log up successful");
        }
        public static ReturnInformation get103(){
            return new ReturnInformation("103","log in failed");
        }
        public static ReturnInformation get104(){
            return new ReturnInformation("104","log in successful");
        }
        public static ReturnInformation get105(){
            return new ReturnInformation("105","user not logged in");
        }
        public static ReturnInformation get106(){
            return new ReturnInformation("106","log out successful");
        }
    }
}
