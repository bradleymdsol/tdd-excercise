package com.medidata.tdd

case class AvailableToSpend(value: BigDecimal) {
  def <(spentAmount: SpendAmount): Boolean = {
    value < spentAmount.value
  }
}

object AvailableToSpend {
  def apply(availableToSpend: String) = new AvailableToSpend(BigDecimal(availableToSpend))
}
