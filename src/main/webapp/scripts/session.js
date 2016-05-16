function passBySession(id, image) {
    sessionStorage.setItem('id_to_load', id);
    sessionStorage.setItem("ComicNumberSelected", image);
    window.location.assign("summary.jsp");
}
