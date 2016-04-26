/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdomain;



/**
 *
 * @author Alex 
 */
public class SalariedEmployee extends HourlyEmployee
{   

    public SalariedEmployee(String name, Double rate)
    {
        super(name, rate);
    }
    
    /**
     * This is the override method that will just return a flat gross salary for
     * salaried employees. 
     * @param year
     * @return 
     */
    @Override
    public double getGrossPay(short year)
    {
       return this.rate;
    }
    
}
