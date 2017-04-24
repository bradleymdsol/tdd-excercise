package com.medidata.tdd


class FinancialControl(maximumSpend: MaximumExpenditure) {
  private val totalExpenditure = TotalExpenditure()

  def available(): AvailableToSpend = maximumSpend - totalExpenditure

  def spend(spend: SpendAmount): SpendResult = {
    if (available() < spend) return SpendFailed

    totalExpenditure.append(spend)
    SpendSuccess
  }

}

object FinancialControl {
  def apply(maximumSpend: MaximumExpenditure) = new FinancialControl(maximumSpend)
}
