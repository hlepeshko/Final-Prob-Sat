% Load salted data from the CSV file
salted_data = csvread('salted_PSS2.csv', 1, 0);  % Skip the header row

% Extract salted X and Y values from the data
salted_xvalues = salted_data(:, 1);
salted_yvalues = salted_data(:, 2);

% Smooth the salted Y values
smoothed_y = smooth(salted_yvalues, 5);  

% Plot the smoothed data with salted X values
plot(salted_xvalues, smoothed_y);
set(gca, 'LineWidth', 1, 'FontSize', 12);
xlabel('Salted X values');
ylabel('Smoothed Y values');
title('Smoothed Plot of Salted PSS2');
grid on;

% limit the x-axis range to 0 to 12 (to make it look more like original potter graph)
xlim([0, 12]);

% Save the graph as an image 
saveas(gcf, 'smoothed_PSS2.png');

