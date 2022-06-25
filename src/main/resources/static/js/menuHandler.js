function openMenu(){
    document.getElementById('SideMenu').style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
    document.getElementById('SideMenu').style.boxShadow = '1000px 1000px 1000px 1000px rgba(0, 0, 0, 0.5)';
}
function closeNav() {
    document.getElementById("SideMenu").style.width = "0";
    document.getElementById("main").style.marginLeft = "0";
    document.getElementById('SideMenu').style.boxShadow = '1000px 1000px 1000px 1000px rgba(0, 0, 0, 0.0)';
}