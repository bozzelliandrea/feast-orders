import 'package:auto_route/annotations.dart';
import 'package:flutter/material.dart';

class OrderPage extends StatelessWidget {
  final int orderId;

  const OrderPage({
    Key? key,
    @PathParam() required this.orderId,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Text('Order Page $orderId'),
    );
  }
}
