import 'package:equatable/equatable.dart';

abstract class AbstractModel extends Equatable {

  Map<String, dynamic> toJson();

}
