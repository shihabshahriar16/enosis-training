let movies = Array.from(Array(13).keys())
let schedules = Array.from(Array(140).keys())

movies.shift()
schedules.shift()

console.log(movies)

function randomDate(start, end) {
    return new Date(start.getTime() + Math.random() * (end.getTime() - start.getTime()));
}

function generateRandom(min = 10, max = 60) {

    let difference = max - min;
    let rand = Math.random();
    rand = Math.floor( rand * difference);
    rand = rand + min;
    return rand*10;
}

Array.prototype.random = function () {
    return this[Math.floor((Math.random()*this.length))];
}

// const d = randomDate(new Date(2019, 0, 1), new Date());
// console.log(d);
let i=1;
let s = new Set();
while(i!=101){
    let d = randomDate(new Date(2019, 0, 1), new Date()).toISOString().split('T')[0];
    let m = movies.random();
    let sc = schedules.random();
    let tp = generateRandom();
    while(s.has(`${sc}-${d}`)){
        d = randomDate(new Date(2019, 0, 1), new Date());
        sc = schedules.random();
    }
    s.add(`${sc}-${d}`);
    console.log(`INSERT INTO movie_schedule VALUES(${i}, ${m}, ${sc}, '${d}', ${tp} );`)
    i++;
}