"use strict";

function Percent(
    id,
    years,
    percent
) {
    return {
        id: id, years: years, percent: percent
    }
}

var arrPercent = ${percents}
    function handleClick(index) {
        document.getElementById('per').value = (arrPercent[index].percent).toString();
    }
