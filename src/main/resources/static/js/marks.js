// document.addEventListener("DOMContentLoaded", function () {
//
//     // ============================================
//     // Subject Performance Chart
//     // ============================================
//
//     const subjectChart = document.getElementById("subjectChart");
//
//     if (subjectChart) {
//
//         const performance = JSON.parse(
//             subjectChart.dataset.performance || "[]"
//         );
//
//         const subjectLabels = [];
//         const averageMarks = [];
//
//         performance.forEach(function (item) {
//
//             subjectLabels.push(item.subject);
//             averageMarks.push(item.averageMarks);
//
//         });
//
//         new Chart(subjectChart, {
//
//             type: "bar",
//
//             data: {
//
//                 labels: subjectLabels,
//
//                 datasets: [{
//
//                     label: "Average Marks",
//
//                     data: averageMarks,
//
//                     backgroundColor: "#0d6efd",
//
//                     borderRadius: 10
//
//                 }]
//
//             },
//
//             options: {
//
//                 responsive: true,
//
//                 maintainAspectRatio: false,
//
//                 scales: {
//
//                     y: {
//
//                         beginAtZero: true,
//
//                         max: 100
//
//                     }
//
//                 },
//
//                 plugins: {
//
//                     legend: {
//
//                         display: false
//
//                     }
//
//                 }
//
//             }
//
//         });
//
//     }
//
//     // ============================================
//     // Pass vs Fail Chart
//     // ============================================
//
//     const passFailChart = document.getElementById("passFailChart");
//
//     if (passFailChart) {
//
//         const passed = Number(passFailChart.dataset.passed);
//         const failed = Number(passFailChart.dataset.failed);
//
//         new Chart(passFailChart, {
//
//             type: "doughnut",
//
//             data: {
//
//                 labels: [
//
//                     "Pass",
//
//                     "Fail"
//
//                 ],
//
//                 datasets: [{
//
//                     data: [
//
//                         passed,
//
//                         failed
//
//                     ],
//
//                     backgroundColor: [
//
//                         "#198754",
//
//                         "#dc3545"
//
//                     ],
//
//                     hoverOffset: 15
//
//                 }]
//
//             },
//
//             options: {
//
//                 responsive: true,
//
//                 maintainAspectRatio: false,
//
//                 plugins: {
//
//                     legend: {
//
//                         position: "bottom"
//
//                     }
//
//                 }
//
//             }
//
//         });
//
//     }
//
//     // ============================================
//     // Grade Distribution Chart
//     // ============================================
//
//     const gradeChart = document.getElementById("gradeChart");
//
//     if (gradeChart) {
//
//         const grades = JSON.parse(
//             gradeChart.dataset.grades || "[]"
//         );
//
//         const gradeLabels = [];
//         const gradeCounts = [];
//
//         grades.forEach(function (item) {
//
//             gradeLabels.push(item[0]);
//             gradeCounts.push(item[1]);
//
//         });
//
//         new Chart(gradeChart, {
//
//             type: "pie",
//
//             data: {
//
//                 labels: gradeLabels,
//
//                 datasets: [{
//
//                     data: gradeCounts,
//
//                     backgroundColor: [
//
//                         "#198754",
//                         "#0d6efd",
//                         "#ffc107",
//                         "#20c997",
//                         "#fd7e14",
//                         "#dc3545"
//
//                     ]
//
//                 }]
//
//             },
//
//             options: {
//
//                 responsive: true,
//
//                 maintainAspectRatio: false,
//
//                 plugins: {
//
//                     legend: {
//
//                         position: "bottom"
//
//                     }
//
//                 }
//
//             }
//
//         });
//
//     }
//
// });