/* global axios */
Vue.component("modal", {template: "#modal_template"});
new Vue({
    el: "#productoVUE",
    data: {
        producto: {},
        productos: [],
        productoModal: {
            id: "productoModal",
            header: true,
            title: 'Nuevo Deporte',
            okbtn: 'Aceptar',
            modalSize: 'modal-lg',
            processing: false
        },
        categorias: [
            {id: 1, nombre: 'Quimicos'},
            {id: 1, nombre: 'Cosmeticos'},
            {id: 1, nombre: 'Farmaceuticos'},
            {id: 1, nombre: 'Metalicos'},
            {id: 1, nombre: 'Heramientas'},
            {id: 1, nombre: 'Electricos'},
            {id: 1, nombre: 'Abarrotes'},
            {id: 1, nombre: 'Ferreteria'},
        ],
        fieldSearch: null
    },
    mounted() {
        const $vue = this;
        $vue.all();
    },
    methods: {
        openModalProducto() {
            let $vue = this;
            $vue.producto = {};
            $vue.$refs.productoModal.open();
            setTimeout(() => {
                $("#precio").numeric({negative: false});
                $("#stock").numeric({negative: false});
            }, 500);
        },
        save() {
            let $vue = this;
            axios.post("/producto/save", $vue.producto).then(response => {
                if (response.data.success) {
                    $vue.all();
                    $vue.$refs.productoModal.close();
                    notify2(response.data.message, "success");
                } else {
                    notify2(response.data.message, "error");
                }
            }).catch((err) => {
                notify2(MESSAGES.errorComunicacion, "error");
            });
        },
        eliminar(producto) {
            let $vue = this;
            swal.fire({
                title: "¿ Seguro que quieres Eliminar Producto ?",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#3085d6",
                cancelButtonColor: "#d33",
                confirmButtonText: "Aceptar"
            }).then(result => {
                if (result.value) {
                    axios.post("/producto/delete", producto).then(response => {
                        if (response.data.success) {
                            $vue.all();
                            notify2(response.data.message, "info");
                        } else {
                            notify2(response.data.message, "error");
                        }
                    }).catch((err) => {
                        notify2(MESSAGES.errorComunicacion, "error");
                    });
                }
            });
        },
        all() {
            const $vue = this;
            axios.get("/producto/all").then(response => {
                if (response.data.success) {
                    $vue.productos = response.data.data;
                } else {
                    notify2(response.data.message, "error");
                }
            }).catch((err) => {
                notify2(MESSAGES.errorComunicacion, "error");
            });
        },
        update(producto) {
            const $vue = this;
            $vue.productoModal.title = `Editar ${producto.nombre}`;
            $vue.openModalProducto();
            $vue.producto = producto;
        },
        searchProducto() {
            const $vue = this;
            if ($vue.fieldSearch == null) {
                notify2("Ingrese un nombre o codigo", "error");
                return;
            }
            axios.get("/producto/search", {params: {
                    nombre: $vue.fieldSearch
                }}).then(response => {
                if (response.data.success) {
                    console.log(response);
                    $vue.modalRespuesta(response.data.data);
                } else {
                    notify2(response.data.message, "error");
                }
            }).catch((err) => {
                notify2(MESSAGES.errorComunicacion, "error");
            });
        },
        modalRespuesta(data) {
            swal.fire({
                title: `<strong>${data.nombre}</strong>`,
                html: `<table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>Precio</th>
                                            <th>Categoria</th>
                                            <th>Stock</th>
                                        </tr> 
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>${data.precio}</td>
                                            <td>${data.categoria}</td>
                                            <td>${data.stock}</td>
                                        </tr>
                                    </tbody>
                                </table>`,
            })
        }
    },

});