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
    
    public HashMap<Date,Double> getPayPeriod(int year, int period);
    public boolean hasYear(int year);
    
    public double getHours(Date date);
    public double getTotalHours(int year);
    public void setHoursWorked(Date date, double hours);
    
    public double getFederalTax(int year);
    public double getStateTax(int year);
    public double getMedicareTax(int year);
    public double getSocialSecurityTax(int year);
    
    public double getDeductions(int year);
    public double getGrossPay(int year);
    public double getNetPay(int year);
    
    public FiscalYear getFiscalYear(int year);
    public void addFiscalYear(int year);
    public void addFiscalYearBefore();
    public void addFiscalYearAfter();
    public int[] getFiscalYears();
    
    
}
