<div class="modal fade" id="agregarClienteModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Agregar cliente</h5>
                <button class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>
            
            <form action="${pageContext.request.contextPath}/ServletControlador?accion=insertar" 
                  method="POST" class="was-validated">
                
                <div class="modal-body">
                    <div class="form-group">
                        <label for="nombre" >Nombre: </label>
                        <input required type="text" class="form-control" name="nombre">
                    </div>
                    <div class="form-group">
                        <label for="apellido">Apellido: </label>
                        <input required type="text" class="form-control" name="apellido">
                    </div>
                    <div class="form-group">
                        <label for="email">Correo electrónico: </label>
                        <input required type="email" class="form-control" name="email">
                    </div>
                    <div class="form-group">
                        <label for="telefono">Teléfono: </label>
                        <input required type="text" class="form-control" name="telefono">
                    </div>
                    <div class="form-group">
                        <label for="saldo">Saldo: </label>
                        <input required type="number" class="form-control" name="saldo">
                    </div>
                    <div class="modal-footer">
                        <input type="submit" class="btn btn-primary" value="Guardar">
                    </div>
                </div>
                
            </form>
            
        </div>
    </div>
</div>