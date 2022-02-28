import 'package:auto_route/annotations.dart';
import 'package:flutter/material.dart';

class CategoryPage extends StatelessWidget {
  final int categoryId;

  const CategoryPage({Key? key, @PathParam() required this.categoryId})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Text('Category Page $categoryId'),
    );
  }
}
