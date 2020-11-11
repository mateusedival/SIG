const btnMap = document.querySelector("#btnMap");
const btnForms = document.querySelector('#btnForms');
const btnView = document.querySelector('#btnView');
const maps = document.querySelector('#map')
const forms = document.querySelector('#forms')
const view = document.querySelector('#view')

const alunoBtn = document.querySelector("#alunoBtn")
const cursoBtn = document.querySelector("#cursoBtn")
const shapeBtn = document.getElementById("shapeBtn")
let alunoForms = document.getElementById("alunoForms")
let cursoForms = document.getElementById("cursoForms")
let shapeForms = document.querySelector("#shapeForms")
let nomeCurso = document.querySelector("#nomeCurso")
const cursoPost = document.getElementById("cursoPost")
const alunoPost = document.getElementById("alunoPost")
const shapePost = document.getElementById("shapePost")
let cursoSelectForms = document.querySelector("select#cursoSelectForms")
let nomeAluno = document.querySelector('input#nomeAluno')
let idadeAluno = document.querySelector('input#idadeAluno')
let serieAluno = document.querySelector('input#serieAluno')



const alunoViewBtn = document.querySelector("button#alunoViewBtn")
const cursoViewBtn = document.querySelector("button#cursoViewBtn")
const plantioViewBtn = document.querySelector("button#plantioViewBtn")
const alunoView = document.querySelector("div#alunoView")
const cursoView = document.querySelector("div#cursoView")
const plantioView = document.querySelector("div#plantioView")
const viewBtns = document.querySelector("#viewBtns")




const edit = document.querySelector('#edit');
const plantioEdit = document.querySelector("div#plantioEdit")
const alunoEdit = document.querySelector("div#alunoEdit")
const cursoEdit = document.querySelector("div#cursoEdit")
let nomePlantioEdit = document.querySelector('input#nomePlantioEdit')
let nomeCientificoEdit = document.querySelector('input#nomeCientificoEdit')
let alunoSelectEdit = document.querySelector('select#alunoSelectEdit')
const plantioEditBtn = document.querySelector('#plantioEditBtn')
const plantioCancelBtn = document.querySelector('#plantioCancelBtn')
const alunoCancelBtn = document.querySelector('#alunoCancelBtn')
const cursoCancelBtn = document.querySelector('#cursoCancelBtn')
const alunoEditBtn = document.querySelector("button#alunoEditBtn")
let nomeCursoEdit = document.querySelector("input#nomeCursoEdit")
let nomeAlunoEdit = document.querySelector("input#nomeAlunoEdit")
let idadeAlunoEdit = document.querySelector("input#idadeAlunoEdit")
let serieAlunoEdit = document.querySelector("input#serieAlunoEdit")
let cursoSelectEdit = document.querySelector("select#cursoSelectEdit")
const cursoEditBtn = document.querySelector("button#cursoEditBtn")

plantios = []
cursos = []
alunos = []
update ={
    "plantio": 1,
    "aluno": 1,
    "curso": 1
};

let state = 'map';
let formsState = 'none';
let viewState = 'plantio'

edit.style.display = 'none'
forms.style.display='none'
view.style.display='none'
setDisplay(forms.children,'none')
setDisplay(view.children,'none')
setDisplay(edit.children,'none')

btnMap.onclick = function() {
    if(state = 'forms')
    {
        forms.style.display = 'none';
        btnDisplay('none')
    }
    if(state = 'view')
    {
        view.style.display='none'
        setDisplay(view.children,'none')
    }
    if(state = 'edit')
    {
        edit.style.display = 'none'
        setDisplay(edit.children,'none')
    }
    state = 'map'
    maps.style.display = 'block';
}

btnForms.onclick = function() {
    if(state = 'map')
    {
        maps.style.display = 'none';
    }
    if(state = 'view')
    {
        view.style.display='none'
        setDisplay(view.children,'none')
    }
    if(state = 'edit')
    {
        edit.style.display = 'none'
        setDisplay(edit.children,'none')
    }
    state = 'forms'
    forms.style.display = 'block';
    btnDisplay('block')
}

btnView.onclick = function() {
    if(state = 'map')
    {
        maps.style.display = 'none';
    }
    if(state = 'forms')
    {
        forms.style.display = 'none';
        btnDisplay('none')
    }
    if(state = 'edit')
    {
        edit.style.display = 'none'
        setDisplay(edit.children,'none')
    }
    state = 'view'
    view.style.display = 'block';
    setDisplay(view.children,'block')
    setDisplay(viewBtns.children,'block')
    setView();
}

function btnDisplay(display){
    alunoBtn.style.display = display
    cursoBtn.style.display = display
    shapeBtn.style.display = display
}

alunoBtn.onclick = setForms;
cursoBtn.onclick = setForms;
shapeBtn.onclick = setForms;

function setForms() {
    if(formsState == 'aluno')
        alunoForms.style.display = 'none';
    if(formsState == 'curso')
        cursoForms.style.display = 'none'
    if(formsState == 'shape')
        shapeForms.style.display = 'none'

    let id = this.getAttribute("id");
    cursoSelectForms.innerHTML = ''
    if(id == "alunoBtn")
    {
        alunoForms.style.display = 'block';
        selectGet('curso')
        formsState = 'aluno'
    }
    else if(id == "cursoBtn")
    {
        cursoForms.style.display = 'block'
        formsState = 'curso'
    }
    else
    {
        shapeForms.style.display = 'block';
        formsState = 'shape'
    }
}
function cancelForms() {
    if(formsState = 'aluno')
         alunoForms.style.display = 'none';
    else if(id == "cursoBtn")
        cursoForms.style.display = 'none'
    else
        shapeForms.style.display = 'none';
    formsState = 'none'
}

function setDisplay(array,state) {
    for (i = 0; i < array.length; i++)
    {
        array[i].style.display = state;
    }
}

plantioViewBtn.onclick = setView;
cursoViewBtn.onclick = setView;
alunoViewBtn.onclick = setView;

function setView()
{
    cursoView.style.display = 'none'
    alunoView.style.display = 'none'
    plantioView.style.display = 'none'

    let id = this.getAttribute("id")
    if(id == "plantioViewBtn")
    {
        
        plantioView.style.display = 'block';
        viewState = 'plantio'
    }
    if(id == "alunoViewBtn")
    {
 
        alunoView.style.display = 'block'
        viewState = 'aluno'
    }
    if(id == "cursoViewBtn")
    {
        cursoView.style.display = 'block'
        viewState = 'curso'
    }
    getElements()

}

function getElements()
{
    if(update[viewState] == 0)
        return;
    const URL_TO_FETCH = `http://25.7.142.197:8080/${viewState}/`;

    if(viewState == "plantio") {
        plantios = []
        plantioView.innerHTML = '';
    }
    if(viewState == "aluno") {
        alunos = []
        alunoView.innerHTML = ''
    }
    if(viewState == 'curso') {
        cursos = []
        cursoView.innerHTML = ''
    }
    fetch(URL_TO_FETCH)
    .then(function (response) {
        response.json().then(function (elements) {
            elements.forEach((element) => {
                let div = createDiv(element);
                if(viewState == "plantio") {
                    plantios.push(element)
                    plantioView.appendChild(div)
                }
                if(viewState == "aluno") {
                    alunos.push(element)
                    alunoView.appendChild(div)
                }
                if(viewState == 'curso') {
                    cursos.push(element)
                    cursoView.appendChild(div)
                }

            })
        });
    })
    .catch(function (err) {
        console.error('Failed retrieving information', err);
    });

    update[viewState] = 0;

}

 function createDiv(element){

    let div = document.createElement("div");
    div.setAttribute("class","Element")
    if(viewState == 'plantio') {
        div.setAttribute("idPlantio",element.id)
    }
    if(viewState == 'curso') {
        div.setAttribute("idCurso",element.id)
    }
    if(viewState == 'aluno') {
        div.setAttribute("idAluno",element.id)
    }
    let edit = document.createElement("button");
    edit.setAttribute("class","updateBtn")
    edit.textContent = "Editar"
    edit.onclick = editClick;
    let remove = document.createElement("button");
    remove.setAttribute("class","updateBtn")
    remove.textContent = "Excluir"
    remove.onclick = removeClick;
    let text = document.createElement("p");
    text.setAttribute("class","textUpdate")
    text.textContent = element.nome 

    div.appendChild(text)
    div.appendChild(edit)
    div.appendChild(remove)
   
    return div;
}

function plantioHandler(plantio){
    nomePlantioEdit.value = String(plantio.nome)
    nomeCientificoEdit.value = String(plantio.nomeCientifico)
    alunoSelectEdit.innerHTML ='';
    selectGet('plantio');
    
 } 

function alunoHandler(aluno) {
    nomeAlunoEdit.value = String(aluno.nome)
    idadeAlunoEdit.value = String(aluno.idade)
    serieAlunoEdit.value = String(aluno.serie)
    cursoSelectEdit.innerHTML = ''
    selectGet('aluno');
}

function cursoHandler(curso) {
    nomeCursoEdit.value = String(curso.nome)
}

function editClick() {
    view.style.display = 'none'
    plantioEdit.style.display = 'none'
    alunoEdit.style.display = 'none'
    cursoEdit.style.display = 'none'
    state = 'edit'
    if(viewState == 'plantio') {
        const plantio = plantios.filter((x) => {return x.id == this.parentNode.getAttribute("idPlantio")});
        plantioHandler(plantio[0])
        plantioEdit.setAttribute("idPlantio", plantio[0].id)
        plantioEdit.setAttribute("data", plantio[0].data)
        plantioEdit.style.display = 'block'
    }
    if(viewState == 'aluno') {
        const aluno = alunos.filter((x) => {return x.id == this.parentNode.getAttribute("idAluno")});
        alunoHandler(aluno[0])
        alunoEdit.setAttribute("idAluno", aluno[0].id)
        alunoEdit.style.display = 'block'
    }
    if(viewState == 'curso') {
        const curso = cursos.filter((x) => {return x.id == this.parentNode.getAttribute("idCurso")});
        cursoHandler(curso[0])
        cursoEdit.setAttribute("idCurso", curso[0].id)
        cursoEdit.style.display = 'block'
    }

    edit.style.display = 'block'
    
}

plantioCancelBtn.onclick = cancel;
alunoCancelBtn.onclick = cancel;
cursoCancelBtn.onclick = cancel;

function cancel() {
    edit.style.display = 'none'
    setDisplay(edit.children,'none')

    state = 'view'

    getElements()
    view.style.display = 'block';
}

cursoPost.onclick = () => {
    if(nomeCurso.value == "")
    {
        alert("Nome em branco")
        return;
    }
    let curso = {
        "nome": nomeCurso.value,
        "id": 10
    }
    const URL_TO_FETCH = "http://25.7.142.197:8080/curso/"
    fetch(URL_TO_FETCH, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(curso)
    });
    nomeCurso.value ="";
    formsState = 'none'
    cursoForms.style.display = 'none'
    update["curso"] = 1
}

function selectGet(route){
    let URL_TO_FETCH = 'http://25.7.142.197:8080/';
    
    if(route == 'plantio')
        URL_TO_FETCH+='aluno'
    else
        URL_TO_FETCH+='curso'

    fetch(URL_TO_FETCH)
    .then(function (response) {
        response.json().then(function (elements) {
            elements.forEach((element) => {
                option = document.createElement("option");
                option.setAttribute("value",String(element.id))
                option.innerHTML = String(element.nome);
                if(route == "plantio")
                    alunoSelectEdit.appendChild(option)
                if(route == "aluno")
                    cursoSelectEdit.appendChild(option)
                if(route == "curso")
                    cursoSelectForms.appendChild(option)

            })
        });
    })
    .catch(function (err) {
        console.error('Failed retrieving information', err);
    });
}

alunoPost.onclick = () => {
    if(nomeAluno.value == '')
    {
        alert("Nome em branco")
        return;
    }
    if(idadeAluno.value == '')
    {
        alert("Idade em branco")
        return;
    }
    if(serieAluno.value == "")
    {
        alert("Serie em branco")
        return;
    }
    if(isNaN(idadeAluno.value))
    {
        alert("Idade não é válida")
        return;
    }
    if(isNaN(serieAluno.value))
    {
        alert("Serie não é válida")
        return;
    }
    let aluno = {
        "id": 11,
        "nome": nomeAluno.value,
        "idade": idadeAluno.value,
        "serie": serieAluno.value,
        "idCurso": cursoSelectForms.value
        
    }
    const URL_TO_FETCH = "http://25.7.142.197:8080/aluno/"
    fetch(URL_TO_FETCH, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(aluno)
    });
    nomeAluno.value =";"
    idadeAluno.value = "";
    serieAluno.value = "";
    formsState = 'none'
    alunoForms.style.display = 'none'
    update["aluno"] = 1
}
shapePost.onclick = function(){
    const fileList = new FormData();
    let shp = document.querySelector("input#shp")

    for(file of shp.files)
       fileList.append('fileList', file)
    shp.value = ""
    formsState = 'none'
    shapeForms.style.display = 'none'
    
    

    const URL_TO_FETCH = "http://25.7.142.197:8080/plantio/file"
    fetch(URL_TO_FETCH, {
        method: 'POST',
        body: fileList
    });
    update["plantio"] = 1;
}

plantioEditBtn.onclick = () => {
    const plantio = {
        "id": plantioEdit.getAttribute('idPlantio'),
        "nome": nomePlantioEdit.value,
        "nomeCientifico": nomeCientificoEdit.value,
        "data": plantioEdit.getAttribute('data'),
        "idAluno": alunoSelectEdit.value
    }
    const URL_TO_FETCH = "http://25.7.142.197:8080/plantio/"
    fetch(URL_TO_FETCH, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(plantio)
    });
    update["plantio"] = 1
    cancel()

}
alunoEditBtn.onclick = () => {

    const aluno = {
        "id": alunoEdit.getAttribute('idAluno'),
        "nome": nomeAlunoEdit.value,
        "idade": idadeAlunoEdit.value,
        "serie": serieAlunoEdit.value,
        "idCurso": cursoSelectEdit.value
    }
    const URL_TO_FETCH = "http://25.7.142.197:8080/aluno/"
    fetch(URL_TO_FETCH, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(aluno)
    });
    update["aluno"] = 1
    cancel()
}
cursoEditBtn.onclick = () => {
    const curso = {
        "id": cursoEdit.getAttribute("idCurso"),
        "nome": nomeCursoEdit.value
    }

    const URL_TO_FETCH = "http://25.7.142.197:8080/curso/"
    fetch(URL_TO_FETCH, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(curso)
    });
    update["curso"] = 1
    cancel()
}

function removeClick() {
    let element;
    console.log(viewState)
    if(viewState == "plantio")
        element = plantios.filter((x) => {return x.id == this.parentNode.getAttribute("idPlantio")});
    if(viewState == "curso")
        element = cursos.filter((x) => {return x.id == this.parentNode.getAttribute("idCurso")});
    if(viewState == "aluno")
        element = alunos.filter((x) => {return x.id == this.parentNode.getAttribute("idAluno")});
    const URL_TO_FETCH = `http://25.7.142.197:8080/${viewState}/`
    fetch(URL_TO_FETCH, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(element[0])
    });
    this.parentNode.remove();
    if(viewState == "plantio")
        plantios = plantios.filter((x) => (x.id != element[0].id))
    if(viewState = "curso")
        cursos = cursos.filter((x) => (x.id != element[0].id))
    if(viewState = "aluno")
        alunos = alunos.filter((x) => (x.id != element[0].id))
    console.log(cursos)
    
}