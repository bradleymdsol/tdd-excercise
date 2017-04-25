package com.medidata.tdd


class FinancialControl(maximumExpenditure: MaximumExpenditure) {
  private val totalExpenditure = TotalExpenditure()

  def available(): AvailableExpenditure = {
    maximumExpenditure - totalExpenditure
  }

  def spend(spend: SpendAmount): SpendResult = {
    if (available() < spend) return SpendExceeded

    totalExpenditure.append(spend)
    SpendAdded
  }

}

object FinancialControl {
  def apply(maximumSpend: MaximumExpenditure) = new FinancialControl(maximumSpend)
}
