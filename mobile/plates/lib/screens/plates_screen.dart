import 'package:flutter/material.dart';

class PlatesScreen extends StatelessWidget {
  const PlatesScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Center(
        child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              const Text('Plates screen')
            ]
        )
    );
  }
}
