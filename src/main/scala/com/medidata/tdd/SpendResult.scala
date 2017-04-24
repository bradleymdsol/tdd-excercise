package com.medidata.tdd


sealed trait SpendResult

case object SpendFailed extends SpendResult

case object SpendSuccess extends SpendResult
