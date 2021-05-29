export class CityLocation {
    constructor(
        public id : number,
        public type : string,
        public name : string,
        public address : string,
        public description : string,
        public avgTimeSpent : number,
        public likes : number,
        public dislikes : number,
        public rated : boolean,
        public liked : boolean
    ) {}
}
