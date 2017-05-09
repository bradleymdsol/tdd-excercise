package com.medidata.tdd

import com.medidata.tdd.control.{FinancialControl, SpendAdded, SpendExceeded}
import com.medidata.tdd.domain.{AvailableExpenditure, MaximumExpenditure, SpendAmount}
import org.scalatest.FunSuite

class FinancialControlTest extends FunSuite {

  test("can define a maximum spend") {
    FinancialControl(MaximumExpenditure("100.00"))
  }

  test("can spend less than the maximum spend") {
    FinancialControl(MaximumExpenditure("100.00")).spend(SpendAmount("50"))
  }

  test("can get the available amount") {
    val financialControl = FinancialControl(MaximumExpenditure("100.00"))
    financialControl.spend(SpendAmount("50"))

    assert(financialControl.available() == AvailableExpenditure("50"))
  }

  test("available amount is the total spend removed from the maximum spend") {
    val financialControl = FinancialControl(MaximumExpenditure("120.00"))
    financialControl.spend(SpendAmount("100"))

    assert(financialControl.available() == AvailableExpenditure("20"))
  }

  test("can spend multiple amount") {
    val financialControl = FinancialControl(MaximumExpenditure("150.00"))
    financialControl.spend(SpendAmount("50"))
    financialControl.spend(SpendAmount("60"))

    assert(financialControl.available() == AvailableExpenditure("40"))
  }

  test("will respond with spend failed when amount is greater than maximum spend") {
    val financialControl = FinancialControl(MaximumExpenditure("150.00"))

    assert(financialControl.spend(SpendAmount("600")) == SpendExceeded)
  }

  test("available will remain unchanged with spend failure") {
    val financialControl = FinancialControl(MaximumExpenditure("150.00"))

    assert(financialControl.available() == AvailableExpenditure("150"))
    assert(financialControl.spend(SpendAmount("600")) == SpendExceeded)
    assert(financialControl.available() == AvailableExpenditure("150"))
  }

  test("will respond with Spend Successful when spend is below maximum spend") {
    val financialControl = FinancialControl(MaximumExpenditure("150.00"))

    assert(financialControl.spend(SpendAmount("100")) == SpendAdded)
  }

  test("should consider previous spent amounts for breaching maximum spend") {
    val financialControl = FinancialControl(MaximumExpenditure("150.00"))

    financialControl.spend(SpendAmount("100"))
    assert(financialControl.spend(SpendAmount("60")) == SpendExceeded)
    assert(financialControl.available() == AvailableExpenditure("50"))
  }

  test("can spend exactly the maximum spend") {
    val financialControl = FinancialControl(MaximumExpenditure("150.00"))

    assert(financialControl.spend(SpendAmount("150.00")) == SpendAdded)
    assert(financialControl.available() == AvailableExpenditure("0"))
  }

}
