

addPlaceForm.addEventListener("submit", handleRow)

let touristPlacesIndex = {};
let touristPlacesHTML = [];
function handleRow(e) {
    e.preventDefault();
    const data = new FormData(addPlaceForm);
    addPlaceForm.reset()
    let table = document.getElementById("touristPlaceGrid");
    let id,trow;

    if(data.get('row-id')!=0){
        id = data.get('row-id');
        trow = table.rows.namedItem(`row-${id}`);
    }
    else{
        id = table.rows.length;
        data.set('row-id',id);
        trow = table.insertRow(id);
        trow.setAttribute("id",`row-${id}`);
    }
    touristPlacesIndex[id] = Object.fromEntries(data);
    
    let objectURL = window.URL.createObjectURL(data.get("picture"));
    console.log(objectURL)
    trow.innerHTML = `<td>${data.get("name")}</td>
                        <td>${data.get("address")}</td>
                        <td>${data.get("rating")}</td>
                        <td><img src="${objectURL}" width="100" height="100"></td>
                        <td>
                            <button class="redButton" type="button" onclick="deleteRow(event)">Delete</button>
                            <button class="greenButton" type="button" onclick="updateRow(event)" value="${id}">Update</button>
                        </td>`
    for (const [name, value] of data) {
        console.log(`name ${name} value ${value}`)
    }
    touristPlacesHTML = Array.from(touristPlaceGrid.tBodies[0].rows)
    document.getElementById("tableRating").className = "not-sorted";
    document.getElementById("form-container").style.display = "none";
    document.getElementById("table-container").style.display = "block";
}

function deleteRow(event) {
    let trow = event.target.closest("tr");
    trow.remove();
}

function updateRow(event) {
    let placeForm = document.getElementById("addPlaceForm");
    let formValues = touristPlacesIndex[event.target.value];
    placeForm.elements["row-id"].value = formValues["row-id"];
    placeForm.elements["name"].value = formValues["name"];
    placeForm.elements["address"].value = formValues["address"];
    placeForm.elements["rating"].value = formValues["rating"];
    console.log("value: "+ event.target.value)
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
    let thsort = e.target.getAttribute("classname");
    let thIndex = e.target.cellIndex;
    
    let sortedRows = Array.from(touristPlaceGrid.tBodies[0].rows);
    console.log(touristPlaceGrid.tBodies[0].rows)
    if(thsort=="not-sorted"){
        sortedRows = sortedRows.sort((rowA, rowB) => rowA.cells[thIndex].innerHTML.localeCompare(rowB.cells[thIndex].innerHTML))
        e.target.setAttribute("classname","sorted-asc");
    }
    if(thsort=="sorted-asc"){
        sortedRows.reverse();
        e.target.setAttribute("classname","sorted-dsc");
    }
    if(thsort=="sorted-dsc"){
        sortedRows = touristPlacesHTML
        e.target.setAttribute("classname","not-sorted");
    }
    touristPlaceGrid.tBodies[0].append(...sortedRows);
}

function autocompleteMatch(input) {
    touristPlaceGrid.tBodies[0].append(...touristPlacesHTML);
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
    console.log(searchRows)
    touristPlaceGrid.tBodies[0].innerHTML="";
    console.log(touristPlaceGrid.tBodies[0].children)
    touristPlaceGrid.tBodies[0].append(...searchRows);
  }