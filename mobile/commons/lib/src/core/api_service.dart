import 'package:commons/src/core/abstract_model.dart';
import 'package:http/http.dart' as http;

/// Api Service
/// basic class for invoke rest api expose by feast order backend.
///
/// Usage:
/// get a new instance of the service
/// ApiService service = ApiService.getInstance("http://localhost:8081")
///
/// call rest api
/// - api without parameter:
/// service.api.auth.reset.execute()
///
/// result -> $HOST/api/auth/reset/3
///
/// - api with url parameter:
/// declare a map with the param name and the value
/// Map<String, String> urlParameters = {"id" = "3"}
/// service.api.auth.reset.execute(urlParameters: urlParameters)
///
/// result -> $HOST/api/auth/reset/3
///
/// - api with url parameter and body:
/// declare a map with the param name and the value
/// Map<String, String> urlParameters = {"id" = "3"}
/// Model extend AbstractModel body = {name = "test"}
///
/// service.api.auth.reset.execute(urlParameters: urlParameters, body: body)
///
/// result -> $HOST/api/auth/reset/3
/// payload {"name" = "test"}
class ApiService {
  static late final String baseUrl;

  // private constructor
  ApiService._();

  // public
  _Resource api = _Resource();

  static ApiService getInstance(String baseUrl) {
    ApiService.baseUrl = baseUrl;
    return ApiService._();
  }

  static Future<http.Response>? _execute(_InternalApiModel invoker,
      {Map<String, String>? urlParameters, AbstractModel? dto}) {
    String uri = baseUrl + invoker._endpoint;

    if (urlParameters != null && urlParameters.isNotEmpty) {
      uri = _resolveUriParameters(uri, urlParameters);
    }

    switch (invoker._method) {
      case _RestMethod.get:
        return http.get(Uri.parse(uri));
      case _RestMethod.post:
        return http.post(Uri.parse(uri),
            headers: _headers(), body: dto?.toJson());
      case _RestMethod.put:
        return http.put(Uri.parse(uri),
            headers: _headers(), body: dto?.toJson());
      case _RestMethod.patch:
        return http.patch(Uri.parse(uri),
            headers: _headers(), body: dto?.toJson());
      case _RestMethod.delete:
        return http.delete(Uri.parse(uri));
      default:
        throw Exception("Http Method not implemented!");
    }
  }

  static Map<String, String> _headers() {
    return {
      'Content-Type': 'application/json; charset=UTF-8',
    };
  }

  static String _resolveUriParameters(
      String uri, Map<String, String> parameters) {
    late String convertedUri;

    for (String key in parameters.keys) {
      final regex = RegExp("{$key}");

      convertedUri = uri.replaceFirst(regex.pattern, parameters[key]!);
    }

    return convertedUri;
  }
}

class _Resource {

  _AuthApi auth = _AuthApi();
  _PrinterApi printer = _PrinterApi();
  _CategoryApi category = _CategoryApi();
  _OrderApi order = _OrderApi();
}

class _AuthApi {
  static const String _endpoint = "/auth";

  _InternalApiModel login =
      _InternalApiModel(_endpoint + "/signin", _RestMethod.post);
  _InternalApiModel register =
      _InternalApiModel(_endpoint + "/signup", _RestMethod.post);
  _InternalApiModel reset =
      _InternalApiModel(_endpoint + "/reset", _RestMethod.post);
}

class _PrinterApi {
  static const String _endpoint = "/printer";

  _InternalApiModel getAllPrinters =
      _InternalApiModel(_endpoint + "/list", _RestMethod.get);

  _InternalApiModel getAllConfigurations =
      _InternalApiModel(_endpoint + "/cfg", _RestMethod.get);
  _InternalApiModel createConfiguration =
      _InternalApiModel(_endpoint + "/cfg", _RestMethod.post);
  _InternalApiModel readConfiguration =
      _InternalApiModel(_endpoint + "/cfg/{id}", _RestMethod.get);
  _InternalApiModel updateConfiguration =
      _InternalApiModel(_endpoint + "/cfg/{id}", _RestMethod.put);
  _InternalApiModel deleteConfiguration =
      _InternalApiModel(_endpoint + "/cfg/{id}", _RestMethod.delete);

  _InternalApiModel printWithPrinter =
      _InternalApiModel(_endpoint + "/print/{printerName}", _RestMethod.get);
  _InternalApiModel printPdfWithPrinter =
      _InternalApiModel(_endpoint + "/printPdf/{printerName}", _RestMethod.get);
  _InternalApiModel printToFile =
      _InternalApiModel(_endpoint + "/printToFile", _RestMethod.get);
  _InternalApiModel getAllReportTemplates =
      _InternalApiModel(_endpoint + "/reportTemplate", _RestMethod.get);
}

class _CategoryApi {
  static const String _endpoint = "/category";

  _InternalApiModel getAllCategories =
      _InternalApiModel(_endpoint, _RestMethod.get);
  _InternalApiModel getCategory =
      _InternalApiModel(_endpoint + "/{id}", _RestMethod.get);
  _InternalApiModel createCategory =
      _InternalApiModel(_endpoint, _RestMethod.post);
  _InternalApiModel updateCategory =
      _InternalApiModel(_endpoint, _RestMethod.put);
  _InternalApiModel deleteCategory =
      _InternalApiModel(_endpoint + "/{id}", _RestMethod.delete);

  _InternalApiModel getAllMenuItem =
      _InternalApiModel(_endpoint + "/{id}/menuitem", _RestMethod.get);
  _InternalApiModel getMenuItem =
      _InternalApiModel(_endpoint + "/{id}/menuitem/{itemId}", _RestMethod.get);
  _InternalApiModel createMenuItem =
      _InternalApiModel(_endpoint + "/{id}/menuitem", _RestMethod.post);
  _InternalApiModel updateMenuItem =
      _InternalApiModel(_endpoint + "/{id}/menuitem/{itemId}", _RestMethod.put);
  _InternalApiModel deleteMenuItem = _InternalApiModel(
      _endpoint + "/{id}/menuitem/{itemId}", _RestMethod.delete);
}

class _OrderApi {
  static const String _endpoint = "/order";

  _InternalApiModel getAllOrders =
      _InternalApiModel(_endpoint, _RestMethod.get);
  _InternalApiModel getOrder =
      _InternalApiModel(_endpoint + "/{id}", _RestMethod.get);
  _InternalApiModel createOrder =
      _InternalApiModel(_endpoint, _RestMethod.post);
  _InternalApiModel updateOrder = _InternalApiModel(_endpoint, _RestMethod.put);
  _InternalApiModel deleteOrder =
      _InternalApiModel(_endpoint + "/{id}", _RestMethod.delete);
}

class _InternalApiModel {
  final String _endpoint;
  final _RestMethod _method;

  _InternalApiModel(this._endpoint, this._method);

  Future<http.Response>? execute(
      {Map<String, String>? urlParameters, AbstractModel? dto}) {
    ApiService._execute(this, urlParameters: urlParameters, dto: dto);
  }
}

enum _RestMethod { get, post, put, patch, delete }
