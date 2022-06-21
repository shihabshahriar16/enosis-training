

addPlaceForm.addEventListener("submit", handleRow)
addPlaceForm.addEventListener("reset", handleReset)

let touristPlacesIndex = {};
let touristPlacesHTML = [];
let thsort ="not-sorted";

picture.onchange = () => {
    const [file] = picture.files
    if (file) {
        previewImg.style.display = "block"
        previewImg.src = URL.createObjectURL(file)
    }
}

function handleRow(e) {
    e.preventDefault();
    const data = new FormData(addPlaceForm);
    addPlaceForm.reset()
    previewImg.style.display = "none"
    previewImg.src = "#"
    thsort ="not-sorted";
    let table = document.getElementById("touristPlaceGrid").getElementsByTagName('tbody')[0];
    let id,trow;
    let pic = data.get('picture');
    if(data.get('row-id')!=-1){
        id = data.get('row-id');
        trow = table.rows.namedItem(`row-${id}`);       
        pic = touristPlacesIndex[id]['picture'];
    }
    else{
        id = table.rows.length;
        data.set('row-id',id);
        trow = table.insertRow(id);
        trow.setAttribute("id",`row-${id}`);
    }
    if(data.get('picture').name==""){
        data.set('picture',pic);
    }

    touristPlacesIndex[id] = Object.fromEntries(data);
    
    let objectURL = window.URL.createObjectURL(data.get("picture"));
    trow.innerHTML = `<td>${data.get("name")}</td>
                        <td>${data.get("address")}</td>
                        <td>${data.get("rating")}</td>
                        <td style="text-align:center;"><img src="${objectURL}" width="200" height="150"></td>
                        <td id="table-buttons">
                            <button class="redButton" type="button" onclick="deleteRow(event)">Delete</button>
                            <button class="greenButton" type="button" onclick="updateRow(event)" value="${id}">Update</button>
                        </td>`

    touristPlacesHTML = Array.from(touristPlaceGrid.tBodies[0].rows)
    
    document.getElementById("tableRating").className = "not-sorted";
    document.getElementById("form-container").style.display = "none";
    document.getElementById("table-container").style.display = "block";
}

function handleReset(e){
    previewImg.style.display = "none"
    previewImg.src = "#"
}

function deleteRow(event) {
    let trow = event.target.closest("tr");
    
    delete touristPlacesIndex[trow.id]
    touristPlacesHTML = touristPlacesHTML.filter(item => item!=trow)
    trow.remove();
}

function updateRow(event) {
    let placeForm = document.getElementById("addPlaceForm");
    let formValues = touristPlacesIndex[event.target.value];
    placeForm.elements["row-id"].value = formValues["row-id"];
    placeForm.elements["name"].value = formValues["name"];
    placeForm.elements["address"].value = formValues["address"];
    placeForm.elements["rating"].value = formValues["rating"];
    previewImg.style.display = "block";
    previewImg.src = URL.createObjectURL(formValues["picture"]);

    document.getElementById("table-container").style.display = "none";
    document.getElementById("form-container").style.display = "block";
}

createNew.onclick = function () {
    document.getElementById("table-container").style.display = "none";
    document.getElementById("form-container").style.display = "block";
}

backToTable.onclick = function () {
    document.getElementById("addPlaceForm").reset();
    document.getElementById("form-container").style.display = "none";
    document.getElementById("table-container").style.display = "block";
}



touristPlaceGrid.onclick = function(e) {
    if (e.target.tagName != 'TH' || e.target.getAttribute("id")!= "tableRating") return;
    let sortedRows = Array.from(touristPlaceGrid.tBodies[0].rows);

    if(thsort=="not-sorted"){
        sortedRows = sortedRows.sort((rowA, rowB) => rowA.cells[2].innerHTML.localeCompare(rowB.cells[2].innerHTML));
        thsort = "sorted-asc";
    }
    else if(thsort=="sorted-asc"){
        sortedRows.reverse();
        thsort = "sorted-dsc";
    }
    else if(thsort=="sorted-dsc"){
        sortedRows = touristPlacesHTML;
        thsort = "not-sorted";
    }

    touristPlaceGrid.tBodies[0].append(...sortedRows);
}

function autocompleteMatch(input) {
    sortedRows = touristPlacesHTML;
    if(thsort=="sorted-asc"){
        sortedRows = touristPlacesHTML.sort((rowA, rowB) => rowA.cells[2].innerHTML.localeCompare(rowB.cells[2].innerHTML));
    }
    else if(thsort=="sorted-dsc"){
        sortedRows = touristPlacesHTML.sort((rowA, rowB) => rowA.cells[2].innerHTML.localeCompare(rowB.cells[2].innerHTML));
        sortedRows.reverse();
    }
    touristPlaceGrid.tBodies[0].append(...sortedRows);
    if (input == '') {
      return [];
    }
    let reg = new RegExp(input)
    let searchRows = Array.from(touristPlaceGrid.tBodies[0].rows);
    searchRows = searchRows.filter(function(term) {
        if (term.cells[0].innerHTML.match(reg)) {
          return term;
        }
    });
    touristPlaceGrid.tBodies[0].innerHTML="";
    touristPlaceGrid.tBodies[0].append(...searchRows);
  }