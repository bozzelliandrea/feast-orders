import 'package:auto_route/auto_route.dart';
import 'package:auto_route/annotations.dart';
import 'package:flutter/material.dart';
import 'package:orders/categories/components/menu_item.dart';
import 'package:orders/categories/models/menu_item.dart';
import 'package:orders/routes/router.gr.dart';

class CategoryPage extends StatelessWidget {
  final int categoryId;

  // fixme: mock data
  final List<MenuItemModel> menuItems = MenuItemModel.generateMenuItems();

  CategoryPage({
    Key? key,
    @PathParam() required this.categoryId
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ListView.separated(
      padding: EdgeInsets.zero,
      itemBuilder: (context, index) => GestureDetector(
        onTap: () => context.router.push(MenuItemRoute(menuItemId: menuItems[index].id!)),
        child: MenuItem(item: menuItems[index])
      ),
      separatorBuilder: (context, index) => const SizedBox(
        height: 15,
      ),
      itemCount: menuItems.length
    );
  }
}
