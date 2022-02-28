import 'package:flutter/material.dart';
import 'package:auto_route/auto_route.dart';
import 'package:orders/routes/router.gr.dart';

import 'category_item.dart';

class CategoriesPage extends StatelessWidget {
  const CategoriesPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          const Text('Categories Page'),
          CategoryItem(
            name: 'Primi',
            description: 'Contiene il menu dei primi',
            color: Colors.orangeAccent,
            onTap: () => context.router.push(
              CategoryRoute(
                categoryId: 1,
              ),
            ),
          ),
        ],
      ),
    );
  }
}
