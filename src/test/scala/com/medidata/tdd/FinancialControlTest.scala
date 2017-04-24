package com.medidata.tdd

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

    assert(financialControl.available() == AvailableToSpend("50"))
  }

  test("available amount is the total spend removed from the maximum spend") {
    val financialControl = FinancialControl(MaximumExpenditure("120.00"))
    financialControl.spend(SpendAmount("100"))

    assert(financialControl.available() == AvailableToSpend("20"))
  }

  test("can spend multiple amount") {
    val financialControl = FinancialControl(MaximumExpenditure("150.00"))
    financialControl.spend(SpendAmount("50"))
    financialControl.spend(SpendAmount("60"))

    assert(financialControl.available() == AvailableToSpend("40"))
  }

  test("will respond with spend failed when amount is greater than maximum spend") {
    val financialControl = FinancialControl(MaximumExpenditure("150.00"))

    assert(financialControl.spend(SpendAmount("600")) == SpendFailed)
  }

  test("available will remain unchanged with spend failure") {
    val financialControl = FinancialControl(MaximumExpenditure("150.00"))

    assert(financialControl.available() == AvailableToSpend("150"))
    assert(financialControl.spend(SpendAmount("600")) == SpendFailed)
    assert(financialControl.available() == AvailableToSpend("150"))
  }

  test("will respond with Spend Successful when spend is below maximum spend") {
    val financialControl = FinancialControl(MaximumExpenditure("150.00"))

    assert(financialControl.spend(SpendAmount("100")) == SpendSuccess)
  }

  test("should consider previous spent amounts for breaching maximum spend") {
    val financialControl = FinancialControl(MaximumExpenditure("150.00"))

    financialControl.spend(SpendAmount("100"))
    assert(financialControl.spend(SpendAmount("60")) == SpendFailed)
    assert(financialControl.available() == AvailableToSpend("50"))
  }

  test("can spend exactly the maximum spend") {
    val financialControl = FinancialControl(MaximumExpenditure("150.00"))

    assert(financialControl.spend(SpendAmount("150.00")) == SpendSuccess)
    assert(financialControl.available() == AvailableToSpend("0"))
  }

}
