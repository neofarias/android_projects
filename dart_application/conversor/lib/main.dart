import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:async';
import 'dart:convert';
import 'package:intl/intl.dart';

//const request = "https://api.hgbrasil.com/finance?format=json&key=93fde119";
const request =
    "http://data.fixer.io/api/latest?access_key=f9a924faadbb7dce5afab49e43288ac5&format=1";

void main() async {
  runApp(MaterialApp(
    home: Home(),
    theme: ThemeData(hintColor: Colors.amber, primaryColor: Colors.white),
  ));
}

Future<Map> getData() async {
  http.Response response = await http.get(request);
  return json.decode(response.body);
}

class Home extends StatefulWidget {
  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  final realController = TextEditingController();
  final dolarController = TextEditingController();
  final canadianController = TextEditingController();
  final currency = new NumberFormat.decimalPattern();

  double dolar;
  double canadian;
  double real;

  void _realChanged(String text) {
    if (text.isEmpty) {
      dolarController.text = "";
      canadianController.text = "";
    }

    double real = double.parse(text);
    dolarController.text =
        currency.format((this.dolar / this.real) * real);
    canadianController.text =
        currency.format((this.canadian / this.real) * real);
  }

  void _dolarChanged(String text) {
    if (text.isEmpty) {
      realController.text = "";
      canadianController.text = "";
    }

    double dolar = double.parse(text);
    realController.text =
        currency.format((this.real / this.dolar) * dolar);
    canadianController.text =
        currency.format((this.canadian / this.dolar) * dolar);
  }

  void _canadianChanged(String text) {
    if (text.isEmpty) {
      realController.text = "";
      dolarController.text = "";
    }

    double canadian = double.parse(text);
    realController.text =
        currency.format((this.real / this.canadian) * canadian);
    dolarController.text =
        currency.format((this.dolar / this.canadian) * canadian);
  }

  void _resetField() {
    this.realController.text = "";
    this.dolarController.text = "";
    this.canadianController.text = "";
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.black,
      appBar: AppBar(
        title: Text("\$ Conversor de Moedas \$"),
        backgroundColor: Colors.amber,
        centerTitle: true,
        actions: <Widget>[
          IconButton(
            icon: Icon(Icons.refresh),
            onPressed: _resetField,
          )
        ],
      ),
      body: FutureBuilder<Map>(
          future: getData(),
          builder: (context, snapshot) {
            switch (snapshot.connectionState) {
              case ConnectionState.none:
              case ConnectionState.waiting:
                return Center(
                  child: Text(
                    "Carregando Dados",
                    style: TextStyle(color: Colors.amber, fontSize: 25.0),
                    textAlign: TextAlign.center,
                  ),
                );
              default:
                if (snapshot.hasError) {
                  return Center(
                    child: Text(
                      "Erro ao Carregar Dados...",
                      style: TextStyle(color: Colors.amber, fontSize: 25.0),
                      textAlign: TextAlign.center,
                    ),
                  );
                } else {
                  real = snapshot.data['rates']['BRL'];
                  dolar = snapshot.data['rates']['USD'];
                  canadian = snapshot.data["rates"]['CAD'];
                  return SingleChildScrollView(
                    padding: EdgeInsets.all(10.0),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.stretch,
                      children: <Widget>[
                        Icon(Icons.monetization_on,
                            size: 150.0, color: Colors.amber),
                        buildTextField(
                            "Reais", "R\$", realController, _realChanged),
                        Divider(),
                        buildTextField(
                            "Dólares", "US\$", dolarController, _dolarChanged),
                        Divider(),
                        buildTextField("Canadian Dollar", "C\$",
                            canadianController, _canadianChanged),
                      ],
                    ),
                  );
                }
            }
          }),
    );
  }
}

Widget buildTextField(
    String label, String prefix, TextEditingController val, Function calc) {
  return TextField(
    focusNode: FocusNode(

    ),
    controller: val,
    decoration: InputDecoration(
        labelText: label,
        labelStyle: TextStyle(color: Colors.amber),
        prefixText: prefix,
        border: OutlineInputBorder()),
    style: TextStyle(color: Colors.amber, fontSize: 25.0),
    keyboardType: TextInputType.number,
    onChanged: calc,
  );
}
