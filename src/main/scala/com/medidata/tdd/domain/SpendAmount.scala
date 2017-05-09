package com.medidata.tdd.domain

case class SpendAmount(value: BigDecimal) extends AnyVal

object SpendAmount {
  def apply(spendAmount: String) = new SpendAmount(BigDecimal(spendAmount))
}
