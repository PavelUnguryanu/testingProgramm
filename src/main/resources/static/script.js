document.addEventListener("DOMContentLoaded", () => {
    loadQuestions();
});

async function loadQuestions() {
    const response = await fetch('/api/questions/random');
    if (!response.ok) {
        console.error('Ошибка при загрузке вопросов:', response.statusText);
        return;
    }

    const questions = await response.json();
    console.log("Полученные вопросы:", questions);

    let html = '<div class="card p-4 shadow-sm">';
    questions.forEach((q, index) => {
        if (!q.text || !q.options) return;

        html += `<div class="mb-4 question-block" id="question_${index}">
                    <h4 class="text-dark">${index + 1}. ${q.text}</h4>
                    <div class="form-check">`;

        q.options.forEach((option, optIndex) => {
            const uniqueName = `question_${index}`;
            const inputId = `q${index}_opt${optIndex}`;
            html += `
                <input class="form-check-input" type="radio" name="${uniqueName}" value="${option.id}" data-correct="${option.isCorrect}" id="${inputId}">
                <label class="form-check-label" for="${inputId}">${option.text}</label><br>
            `;
        });

        html += `</div></div>`;
    });

    html += '</div>';
    document.getElementById("quiz").innerHTML = html;

    // Очищаем старый результат и добавляем кнопку "Проверить"
    document.getElementById("result").innerHTML = `
        <button id="checkButton" class="btn btn-success btn-lg mt-3">Проверить</button>
    `;

    document.getElementById("checkButton").addEventListener("click", checkAnswers);
}

function checkAnswers() {
    let correctCount = 0;

    document.querySelectorAll(".question-block").forEach(questionBlock => {
        const selectedOption = questionBlock.querySelector("input[type=radio]:checked");

        if (!selectedOption) {
            // Если пользователь НЕ выбрал ответ, просто пропускаем этот вопрос
            return;
        }

        const isCorrect = selectedOption.dataset.correct === "true";
        const labels = questionBlock.querySelectorAll("label");

        if (isCorrect) {
            selectedOption.nextElementSibling.classList.add("text-success", "fw-bold"); // Зеленый текст
            selectedOption.nextElementSibling.innerHTML += " ✅";
            correctCount++;
        } else {
            selectedOption.nextElementSibling.classList.add("text-danger", "fw-bold"); // Красный текст
            selectedOption.nextElementSibling.innerHTML += " ❌";

            // Подсвечиваем правильный ответ зеленым
            labels.forEach(label => {
                const input = document.getElementById(label.htmlFor);
                if (input && input.dataset.correct === "true") {
                    label.classList.add("text-success", "fw-bold");
                    label.innerHTML += " ✅";
                }
            });
        }
    });

    // Очищаем старый результат перед добавлением нового
    document.getElementById("result").innerHTML = `
        <div class="alert alert-info mt-4 text-center">
            <strong>Вы правильно ответили на ${correctCount} из 20 вопросов!</strong>
        </div>
        <button id="restartButton" class="btn btn-primary btn-lg mt-3">Начать заново</button>
    `;

    document.getElementById("restartButton").addEventListener("click", restartQuiz);
}

function restartQuiz() {
    loadQuestions(); // Загружаем новые вопросы
}
