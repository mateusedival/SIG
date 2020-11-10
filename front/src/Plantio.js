export function soma() {
    return 2;
}
function btnDisplay(display){
    alunoBtn.style.display = display
    cursoBtn.style.display = display
    shapeBtn.style.display = display
}

function setForms() {
    let id = this.getAttribute("id");
    if(id == "alunoBtn")
    {
        alunoForms.style.display = 'block';
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