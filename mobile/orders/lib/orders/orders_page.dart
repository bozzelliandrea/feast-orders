import 'package:flutter/material.dart';
import 'package:auto_route/auto_route.dart';
import 'package:orders/routes/router.gr.dart';
import 'package:orders/orders/order_item.dart';

class OrdersPage extends StatelessWidget {
  const OrdersPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          const Text('Orders Page'),
          OrderItem(
            number: 1,
            onTap: () => context.router.push(
              OrderRoute(
                orderId: 1,
              ),
            ),
          ),
        ],
      ),
    );
  }
}
