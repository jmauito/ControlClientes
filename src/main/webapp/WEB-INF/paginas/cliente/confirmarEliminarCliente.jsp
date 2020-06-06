<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmar eliminar cliente</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/b4bd5a3d00.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <!-- Cabecero -->
        <jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp"></jsp:include>
        <form action="${pageContext.request.contextPath}/ServletControlador?accion=eliminar&idCliente=${cliente.idCliente}"
              method = "POST" class="was-validated">
            
            <section id="details">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <div class="card">
                                <div class="card-header">
                                    <h4>Se eliminará el sigiente Cliente</h4>
                                </div>
                                
                                <div class="card-body">
                                    <div class="form-group">
                                        <label>Nombre: ${cliente.nombre} </label>
                                        
                                    </div>
                                    <div class="form-group">
                                        <label>Apellido: ${cliente.apellido} </label>
                                    </div>
                                    <div class="form-group">
                                        <label> correo electrónico: ${cliente.email} </label>
                                    </div>
                                    <div class="form-group">
                                        <label>Teléfono: ${cliente.telefono}</label>
                                    </div>
                                    <div class="form-group">
                                        <label>Saldo: ${cliente.saldo}</label>
                                        
                                    </div>
                                    
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </section>
            
            <!-- Botones de navegación -->
            <jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacionEliminacion.jsp"></jsp:include>
        </form>
            <!-- Pie de página -->
        <jsp:include page="/WEB-INF/paginas/comunes/piePagina.jsp"></jsp:include>

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    </body>
</html>
