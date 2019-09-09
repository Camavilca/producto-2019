Vue.component("modal", {template: "#modal_template"});
new Vue({
    el: "#deportesVUE",
    data: {
        deporte: {},
        deportes: [],
        modalDeporte: {
            id: "modalDeporte",
            header: true,
            title: 'Nuevo Deporte',
            okbtn: 'Aceptar',
            modalSize: 'modal-lg',
            processing: false
        },
    },
    mounted() {
        const $vue = this;
        $vue.all();
    },
    methods: {
        openModalDeporte() {
            let $vue = this;
            $vue.deporte = {};
            $vue.$refs.modalDeporte.open();
        },
        save() {
            let $vue = this;
            axios.post("/admin/save", $vue.deporte).then(response => {
                if (response.data.success) {
                    $vue.all();
                    $vue.$refs.modalDeporte.close();
                    notify2(response.data.message, "success", "Deporte<br/>", "fa fa-check");
                } else {
                    notify2(response.data.message, "error");
                }
            }).catch((err) => {
                notify2(MESSAGES.errorComunicacion, "error");
            });
        },
        eliminar(deporte) {
            let $vue = this;
            swal.fire({
                title: "¿ Seguro que quieres Eliminar ?",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#3085d6",
                cancelButtonColor: "#d33",
                confirmButtonText: "Aceptar"
            }).then(result => {
                if (result.value) {
                    axios.post("/admin/delete", deporte).then(response => {
                        if (response.data.success) {
                            $vue.all();
                            notify2(response.data.message, "info", "ssssssssss");
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
            axios.get("/admin/all").then(response => {
                if (response.data.success) {
                    console.log(response.data.data)
                    $vue.deportes = response.data.data;
                } else {
                    notify2(response.data.message, "error");
                }
            }).catch((err) => {
                notify2(MESSAGES.errorComunicacion, "error");
            });
        },
        update(deporte) {
            const $vue = this;
            $vue.modalDeporte.title = `Editar ${deporte.nombre}`;
            $vue.openModalDeporte();
            $vue.deporte = deporte;
        }
    }
});