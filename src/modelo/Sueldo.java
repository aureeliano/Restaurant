package modelo;

public class Sueldo
{
    private static double remBasic = 50000;
    
    /**
     *  Metodo que calcula el sueldo final de cada mozo,remuneración básica: 5% más por cada hijo. <br>
     * @param cantHijos del mozo. <br>
     * @return double sueldo final del mozo. <br>
     */
    public static double calculaSueldo(int cantHijos){
        return remBasic*(1+0.05*cantHijos);
    }

	public void setRemBasic(double remBasic) {
        this.remBasic = remBasic;
    }

    public double getRemBasic() {
        return remBasic;
    }
}
