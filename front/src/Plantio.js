
export function createDiv(){
    let div = document.createElement("div").style.cssText = "border: 2px solid black; width: 100%; display: flex; justify-content: center; border-radius: 15px;";

    let edit = document.createElement("button").style.cssText = "border: 1px solid black; width: 15%; ";
    let remove = document.createElement("button").style.cssText = "border: 1px solid black; width: 15%; ";
    let text = document.createElement("p").style.cssText = "width: 70%;  border-radius: 15px;";
    div.appendChild(edit)
    div.appendChild(remove)
    div.appendChild(text)
    return div;
}

