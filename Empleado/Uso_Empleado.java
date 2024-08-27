package Empleado;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;

public class Uso_Empleado {
    public static void main(String[] args) {


        Jefatura jefe_RRHH = new Jefatura("Andres Montes Jimenez", 70000, 2009, 04, 15);

        jefe_RRHH.estableceIncentivo(4000);

        Empleado[] misEmpleados = new Empleado[6];

        misEmpleados[0] = new Empleado("Alfonso Gras Rama", 25000, 2024, 03, 12);
        misEmpleados[1] = new Empleado("Francisco Gomez Garcia", 25000, 1995, 06, 22);
        misEmpleados[2] = new Empleado("Maria Martin Alvarez", 15000, 2007, 04, 28);
        misEmpleados[3] = new Empleado("Antonio Fernandez Ayuso");
        misEmpleados[4] = jefe_RRHH; //(polimorfismo principio de sustitucion) un jefe debe ser un empleado, por eso sustituye el (new=empleado)
        misEmpleados[5] = new Jefatura("Julia Gutierrez Perez", 55000, 1999, 5, 8);
        //Casting:
        Jefatura jefa_finanzas = (Jefatura) misEmpleados[5];
        jefa_finanzas.estableceIncentivo(5000);


        // ---------> esta es la forma de hacerlo con bucles mejorados, ahorrando codigo */

        for (Empleado e : misEmpleados) {

            e.aumentaSueldo(5);

        }
        //este metodo sort ordena los empleados(obliga a en la clase empleados implementar el metodo comparable y creando la variable compareTo)
        Arrays.sort(misEmpleados);

        for (Empleado e : misEmpleados) {

            System.out.println("Id: " + e.dameId() + " Nombre : " + e.dameNombre() + " Salario : " + e.dameSueldo() + " Fecha de alta : " + e.dameFecha());

        }


    }
}

class Empleado implements Comparable {

    public Empleado(String nom, double sue, int agno, int mes, int dia) {//constructor

        nombre = nom;
        sueldo = sue;
        GregorianCalendar calendario = new GregorianCalendar(agno, mes - 1, dia);
        altaContrato = calendario.getTime();
        Id = IDSiguiente;
        ++IDSiguiente;


    }

    public Empleado(String nom) {

        this(nom, 30000, 2000, 01, 01);


    }


    //--------------METODOS
    public String dameNombre() {//getter

        return nombre;
    }
    public int dameId() {//getter

        return ++Id;
    }

    public double dameSueldo() {//getter

        return sueldo;
    }

    public Date dameFecha() {//getter

        return altaContrato;
    }

    public void aumentaSueldo(double porcentaje) {//setter

        double aumento = sueldo * porcentaje / 100;
        sueldo += aumento;

    }

    //--------------------VARIABLES ENCAPSULADAS
    private String nombre;

    private double sueldo;
    private static int IDSiguiente;

    private int Id;

    private Date altaContrato;

    public int compareTo(Object miObjeto) {
        Empleado otroEmpleado = (Empleado) miObjeto;
        if (this.Id < otroEmpleado.Id) {
            return -1;
        }
        if (this.Id> otroEmpleado.Id){
            return 1;
        }
        return 0;
    }
}

class Jefatura extends Empleado {

    public Jefatura(String nom, double sue, int agno, int mes, int dia) {

        super(nom, sue, agno, mes, dia);

    }

    //variable privada de jefatura

    public void estableceIncentivo(double b) {

        incentivo = b;

    }

    private double incentivo;

    public double dameSueldo() { //super aqui se utiliza para que llame al dameSueldo de la clase padre (empleado)

        double sueldoJefe = super.dameSueldo();

        return sueldoJefe + incentivo;

    }


}


