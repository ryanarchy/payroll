/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdomain;

import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author ryanarchy
 */
public interface Employee
{
    public void setName(String name);
    public String getName();
    
    public void setPayRate(double rate);
    public double getPayRate();
    
    public HashMap<Date,Double> getPayPeriod(short year, short period);
    public boolean hasYear(short year);
    
    public double getHours(Date date);
    public double getTotalHours(short year);
    public void setHoursWorked(Date date, double hours);
    
    public double getFederalTax(short year);
    public double getStateTax(short year);
    public double getMedicareTax(short year);
    public double getSocialSecurityTax(short year);
    
    public double getDeductions(short year);
    public double getGrossPay(short year);
    public double getNetPay(short year);
    
    public FiscalYear getFiscalYear(short year);
    
    
}
