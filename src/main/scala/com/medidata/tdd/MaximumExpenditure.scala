package com.medidata.tdd

case class MaximumExpenditure(value: BigDecimal) {
  def -(totalSpend: TotalExpenditure): AvailableToSpend = {
    AvailableToSpend(value - totalSpend.spent)
  }
}

object MaximumExpenditure {
  def apply(maximumSpend: String) = new MaximumExpenditure(BigDecimal(maximumSpend))
}
