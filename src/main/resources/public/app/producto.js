/* global axios, Vue */
Vue.component('multiselect', {
    mixins: [window.VueMultiselect.default],
    props: {
        selectLabel: {
            default: ""
        }
    }
});
Vue.component("modal", {template: "#modal_template"});
new Vue({
    el: "#productoVUE",
    data: {
        producto: {},
        categoria: {},
        productos: [],
        categorias: [],
        encontrados: null,
        productoModal: {
            id: "productoModal",
            header: true,
            title: 'Nuevo Producto',
            okbtn: 'Guardar',
            modalSize: 'modal-lg',
            processing: false
        },
        categoriaModal: {
            id: "categoriaModal",
            header: true,
            title: 'Nuevo Categoria',
            okbtn: 'Guardar',
            modalSize: 'modal-lg',
            processing: false
        },
        encontrados: {
            id: "encontrados",
            header: true,
            title: 'Productos Encontrados',
            modalSize: 'modal-lg',
            processing: false,
            footer: false,
            showaccept: false
        },
        fieldSearch: null,
    },
    mounted() {
        const $vue = this;
        $vue.all();
        $vue.allCategoria();
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
        openModalCategoria() {
            let $vue = this;
            $vue.categoria = {};
            $vue.$refs.categoriaModal.open();
        },
        save() {
            let $vue = this;
            if ($vue.producto.categoriaEnum != null || typeof $vue.producto.categoriaEnum != 'undefined') {
                $vue.producto.categoria = $vue.producto.categoriaEnum.nombre;
            }
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
            console.log($vue.producto);
        },
        saveCategoria() {
            let $vue = this;
            if ($vue.categoria.nombre == null
                    || typeof $vue.categoria.nombre == 'undefined') {
                return;
            }
            axios.post("/producto/saveCategoria", $vue.categoria).then(response => {
                if (response.data.success) {
                    $vue.allCategoria();
                    $vue.categoria.nombre = '';
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
        eliminarCategoria(categoria) {
            let $vue = this;
            swal.fire({
                title: "¿ Seguro que quieres Eliminar Categoria ?",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#3085d6",
                cancelButtonColor: "#d33",
                confirmButtonText: "Aceptar"
            }).then(result => {
                if (result.value) {
                    axios.post("/producto/deleteCategoria", categoria).then(response => {
                        if (response.data.success) {
                            $vue.allCategoria();
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
        allCategoria() {
            const $vue = this;
            axios.get("/producto/allCategoria").then(response => {
                if (response.data.success) {
                    $vue.categorias = response.data.data;
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
            axios.get("/producto/search", {params: {nombre: $vue.fieldSearch}})
                    .then(response => {
                        if (response.data.success) {
                            $vue.modalRespuesta(response.data.data);
                        } else {
                            notify2(response.data.message, "error");
                        }
                    }).catch((err) => {
                notify2(MESSAGES.errorComunicacion, "error");
            });
        },
        modalRespuesta(productos) {
            this.encontrados = productos;
            this.$refs.encontrados.open();
        }
    },

});