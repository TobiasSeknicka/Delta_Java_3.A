public class Calculator {
    private String currnecy;
    public String test2 ="5";

    public Calculator(String currnecy) {
        this.currnecy = currnecy;
    }

    public Calculator(int currency) {

    }

    public void test(){

    }

    public int add(int a, int b){
        if(this.currnecy.equals("USD")){
            // ...
        }

        return a + b;
    }
}
