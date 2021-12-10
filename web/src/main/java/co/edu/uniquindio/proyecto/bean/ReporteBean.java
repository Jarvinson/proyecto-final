package co.edu.uniquindio.proyecto.bean;


import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.ComentarioServicio;
import co.edu.uniquindio.proyecto.servicios.CompraServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import lombok.Getter;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;


import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.hbar.HorizontalBarChartDataSet;
import org.primefaces.model.charts.hbar.HorizontalBarChartModel;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.primefaces.model.charts.polar.PolarAreaChartDataSet;
import org.primefaces.model.charts.polar.PolarAreaChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class ReporteBean implements Serializable {

    @Autowired
    private CompraServicio compraServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Autowired
    private ComentarioServicio comentarioServicio;

    @Getter
    private BarChartModel barModel;

    @Getter
    private PieChartModel pieModel;

    @Getter
    private HorizontalBarChartModel hbarModel;

    @Getter
    private PolarAreaChartModel polarAreaModel;

    @Getter
    private DonutChartModel donutModel;


    @PostConstruct
    public void init(){
        createBarModel();
        createPieModel();
        createHorizontalBarModel();
        createPolarAreaModel();
        createDonutModel();
    }

    public void createBarModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Número de ventas por mes");

        List<Number> values = new ArrayList<>();


        int numCompras = 0;
        for (Compra c:compraServicio.listaCompras()) {

            for (int j = 1; j <=12; j++){
                values.add(0);
                if(c.getFechaCompra().getMonthValue()==j){
                    numCompras += Integer.parseInt(String.valueOf(values.get(j-1))) + 1;
                    values.set(j-1, numCompras);
                    numCompras = 0;
                }
            }
            barDataSet.setData(values);
        }

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        labels.add("August");
        labels.add("September");
        labels.add("October ");
        labels.add("November");
        labels.add("December");
        data.setLabels(labels);
        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Bar Chart");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barModel.setOptions(options);
    }

    public void createHorizontalBarModel() {
        hbarModel = new HorizontalBarChartModel();
        ChartData data = new ChartData();

        HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
        hbarDataSet.setLabel("Total venta por mes");

        List<Number> values = new ArrayList<>();

        double valorCompra = 0;
        for (Compra c:compraServicio.listaCompras()) {
            for (DetalleCompra dc: c.getDetalleCompra()) {
                for (int j = 1; j <=12; j++){
                    values.add(0);
                    if(c.getFechaCompra().getMonthValue()==j){
                        valorCompra += Double.parseDouble(String.valueOf(values.get(j-1))) + (dc.getPrecioProducto()*dc.getUnidades());
                        values.set(j-1, valorCompra);
                        valorCompra = 0;
                    }
                }
                hbarDataSet.setData(values);
            }
        }

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        hbarDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        hbarDataSet.setBorderColor(borderColor);
        hbarDataSet.setBorderWidth(1);

        data.addChartDataSet(hbarDataSet);

        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        labels.add("August");
        labels.add("September");
        labels.add("October ");
        labels.add("November");
        labels.add("December");
        data.setLabels(labels);
        hbarModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addXAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Horizontal Bar Chart");
        options.setTitle(title);

        hbarModel.setOptions(options);
    }

    private void createPieModel() {
        pieModel = new PieChartModel();
        ChartData data = new ChartData();

        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = new ArrayList<>();

        int productos = 0;
        for (Compra c:compraServicio.listaCompras()) {

            for (DetalleCompra dc: c.getDetalleCompra()) {
                for (int j = 1; j <=12; j++){
                    if(values.size() <= 12){
                        values.add(0);
                    }
                    if(c.getFechaCompra().getMonthValue()==j){
                        productos += Integer.parseInt(String.valueOf(values.get(j-1))) + dc.getUnidades();
                        values.set(j-1, productos);
                        productos = 0;
                    }
                }
            }
            dataSet.setData(values);
        }

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.5)");
        bgColor.add("rgba(255, 159, 64, 0.5)");
        bgColor.add("rgba(255, 205, 86, 0.5)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(127, 255, 0, 0.5)");
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 69, 0, 0.5)");
        dataSet.setBackgroundColor(bgColor);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        labels.add("August");
        labels.add("September");
        labels.add("October ");
        labels.add("November");
        labels.add("December");
        data.setLabels(labels);


        pieModel.setData(data);
    }
    int cont = 0;
    public void createDonutModel() {
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        int productos = 0;
        for (Producto p:productoServicio.listarTodosProductos()) {
            for (int i = 0; i < productoServicio.listarTodosProductos().size(); i++){

                if(values.size() < productoServicio.listarTodosProductos().size()){
                    values.add(0);
                    try {
                        productos = comentarioServicio.calificacionPromedio(p.getCodigo());
                        System.out.println(productos+"+++"+ cont);
                        values.set(cont, productos);
                        dataSet.setData(values);
                        System.out.println(values);
                        productos = 0;
                        i = productoServicio.listarTodosProductos().size();
                        cont += 1;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 205, 86)");
        bgColors.add("rgb(50, 205, 50)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Televisor");
        labels.add("Tenis");
        labels.add("Gafas");
        labels.add("Audifonos");
        data.setLabels(labels);

        donutModel.setData(data);
    }

    private void createPolarAreaModel() {
        polarAreaModel = new PolarAreaChartModel();
        ChartData data = new ChartData();

        PolarAreaChartDataSet dataSet = new PolarAreaChartDataSet();
        List<Number> values = new ArrayList<>();

        int numProductos = 0;
        for (Producto p:productoServicio.listarTodosProductos()) {

            for (int j = 0; j < ciudadServicio.listarCiudades().size() ; j++){
                if(j < ciudadServicio.listarCiudades().size() ){
                    values.add(0);
                }
                if(p.getCiudad().getCodigo() == ciudadServicio.listarCiudades().get(j).getCodigo()){
                    numProductos += Integer.parseInt(String.valueOf(values.get(j-1))) + 1;
                    values.set(j-1, numProductos);
                    numProductos = 0;
                }
            }
            dataSet.setData(values);

        }

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(75, 192, 192)");
        bgColors.add("rgb(255, 205, 86)");
        bgColors.add("rgb(201, 203, 207)");
        bgColors.add("rgb(54, 162, 235)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();

        labels.add("Medellin");
        labels.add("Pereira");
        labels.add("Armenia");
        labels.add("Bogotá");

        data.setLabels(labels);

        polarAreaModel.setData(data);
    }

}
